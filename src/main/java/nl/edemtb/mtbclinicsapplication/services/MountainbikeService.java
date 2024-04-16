package nl.edemtb.mtbclinicsapplication.services;

import jakarta.transaction.Transactional;
import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeInputDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.MountainbikeMapper;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.models.Picture;
import nl.edemtb.mtbclinicsapplication.repositories.MountainbikeRepository;
import nl.edemtb.mtbclinicsapplication.repositories.PictureUploadRepository;
import nl.edemtb.mtbclinicsapplication.repositories.RentalRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MountainbikeService {

    private final MountainbikeRepository mountainbikeRepository;
    private final MountainbikeMapper mountainbikeMapper;
    private final PictureService pictureService;
    private final PictureUploadRepository uploadRepository;
    private final RentalRepository rentalRepository;


    public MountainbikeService(MountainbikeRepository mountainbikeRepository, MountainbikeMapper mountainbikeMapper, PictureService pictureService, PictureUploadRepository uploadRepository, RentalRepository rentalRepository) {
        this.mountainbikeRepository = mountainbikeRepository;
        this.mountainbikeMapper = mountainbikeMapper;
        this.pictureService = pictureService;
        this.uploadRepository = uploadRepository;
        this.rentalRepository = rentalRepository;
    }

    public List<MountainbikeDto> getAllMountainbikes() {
        List<Mountainbike> mtbList = mountainbikeRepository.findAll();
        return transferMtbListToDtoList(mtbList);
    }


    public MountainbikeDto getMountainbikeById(Long id) {
        Optional<Mountainbike> mtbOptional = mountainbikeRepository.findById(id);
        if (mtbOptional.isPresent()) {
            Mountainbike mtb = mtbOptional.get();
            return mountainbikeMapper.transferToDto(mtb);
        } else {
            throw new RecordNotFoundException(" Geen Mountainbike gevonden");
        }
    }

    public List<MountainbikeDto> searchBySize(String size) {
        List<Mountainbike> mtbList = mountainbikeRepository.findAllMountainbikesByFrameSizeEqualsIgnoreCase(size);
        return transferMtbListToDtoList(mtbList);
    }

    public List<MountainbikeDto> searchByForAdult(boolean forAdult) {
        List<Mountainbike> trueList = mountainbikeRepository.findByForAdultIsTrue();
        List<Mountainbike> falseList = mountainbikeRepository.findByForAdultIsFalse();
        if (!forAdult) {
            return transferMtbListToDtoList(falseList);
        } else {
            return transferMtbListToDtoList(trueList);
        }
    }

    public MountainbikeDto addMountainbike(MountainbikeInputDto dto) {

        Mountainbike mtb = mountainbikeMapper.transferToMountainbike(dto);
        mountainbikeRepository.save(mtb);

        return mountainbikeMapper.transferToDto(mtb);
    }

    public void deleteMountainbike(@RequestBody Long id) {

        if (mountainbikeRepository.findById(id).isPresent()) {
            mountainbikeRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("geen mountainbike met id: " + id + " gevonden!");
        }
    }

    public MountainbikeDto updateMountainbike(Long id, MountainbikeInputDto inputDto) {

        if (mountainbikeRepository.findById(id).isPresent()) {

            Mountainbike mtb = mountainbikeRepository.findById(id).get();

            mtb.setAmount(inputDto.getAmount());
            mtb.setAvailable(mtb.getAmount() >= 1);
            mountainbikeRepository.save(mtb);
            return mountainbikeMapper.transferToDto(mtb);
        } else {
            throw new RecordNotFoundException("Geen mountainbike gevonden met id: " + id);
        }
    }

    public List<MountainbikeDto> transferMtbListToDtoList(List<Mountainbike> mountainbikes) {
        List<MountainbikeDto> mtbDtoList = new ArrayList<>();

        for (Mountainbike mtb : mountainbikes) {
            MountainbikeDto dto = mountainbikeMapper.transferToDto(mtb);
            mtbDtoList.add(dto);
        }
        return mtbDtoList;
    }

    @Transactional
    public Resource getPictureFromMountainbike(Long id) throws FileNotFoundException {
        Optional<Mountainbike> optionalMtb = mountainbikeRepository.findById(id);
        if(optionalMtb.isEmpty()){
            throw new RecordNotFoundException("Mountainbike " + id+ " not found.");
        }
        Picture picture = optionalMtb.get().getPicture();
        if(picture == null){
            throw new RecordNotFoundException("Mountainbike" + id + " had no photo.");
        }
        return pictureService.downLoadPicture(picture.getFileName());
    }
    @Transactional
    public Mountainbike assignPictureToMountainbike(String filename, Long id){
        Optional<Mountainbike> optionalMtb = mountainbikeRepository.findById(id);
        Optional<Picture> optionalPicture = uploadRepository.findByFileName(filename);

        if(optionalMtb.isPresent() && optionalPicture.isPresent()){
            Picture picture = optionalPicture.get();
            Mountainbike mtb = optionalMtb.get();
            mtb.setPicture(picture);
            return mountainbikeRepository.save(mtb);
        }else{
            throw new RecordNotFoundException("Mountainbike or picture not found.");
        }
    }
    public void assignRentalToMountainbike(Long id, Long rentalId){
        var optionalMtb = mountainbikeRepository.findById(id);
        var optionalRental = rentalRepository.findById(rentalId);

        if(optionalMtb.isPresent() && optionalRental.isPresent()){
            var mountainbike = optionalMtb.get();
            var mountainbikeRental = optionalRental.get();

            mountainbike.setRental(mountainbikeRental);
            mountainbikeRepository.save(mountainbike);
        }else {
            throw new RecordNotFoundException("Mountainbike or rental not found.");}
    }
}

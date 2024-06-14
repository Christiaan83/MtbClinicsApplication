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



    public MountainbikeService(MountainbikeRepository mountainbikeRepository, MountainbikeMapper mountainbikeMapper, PictureService pictureService, PictureUploadRepository uploadRepository) {
        this.mountainbikeRepository = mountainbikeRepository;
        this.mountainbikeMapper = mountainbikeMapper;
        this.pictureService = pictureService;
        this.uploadRepository = uploadRepository;
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
            throw new RecordNotFoundException(" No mountainbike found with id " + id);
        }
    }

    public List<MountainbikeDto> searchBySize(String size) {
        List<Mountainbike> mtbList = mountainbikeRepository.findAllMountainbikesByFrameSizeEqualsIgnoreCase(size);
        return transferMtbListToDtoList(mtbList);
    }

    public List<MountainbikeDto> searchBySizeAndForAdult(String size, Boolean forAdult, Boolean fullSuspension) {
       List<Mountainbike> mtblist = mountainbikeRepository.searchBySizeAndForAdult(size, forAdult, fullSuspension);

            return transferMtbListToDtoList(mtblist);
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
            throw new RecordNotFoundException(" No mountainbike found with id " + id);
        }
    }

    public MountainbikeDto updateMountainbike(Long id, MountainbikeInputDto inputDto) {

        if (mountainbikeRepository.findById(id).isPresent()) {

            Mountainbike mtb = mountainbikeRepository.findById(id).get();

            if(inputDto.getAmount() != null) {
                mtb.setAmount(inputDto.getAmount());
            }
            if(inputDto.getAvailable() != null) {
                mtb.setAvailable(inputDto.getAvailable());
            }
            if(inputDto.getPricePerDayPart() != null){
                mtb.setPricePerDayPart(inputDto.getPricePerDayPart());
            }
            mtb.setAvailable(mtb.getAmount() >= 1);

            mountainbikeRepository.save(mtb);
            return mountainbikeMapper.transferToDto(mtb);
        } else {
            throw new RecordNotFoundException(" No mountainbike found with id " + id);
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
    public Resource getPictureFromMountainbike(Long id) {
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
}

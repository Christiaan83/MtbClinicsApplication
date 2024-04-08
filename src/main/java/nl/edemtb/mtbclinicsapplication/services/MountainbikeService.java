package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.MountainbikeMapper;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.repositories.MountainbikeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MountainbikeService {

    private final MountainbikeRepository mountainbikeRepository;
    private final MountainbikeMapper mountainbikeMapper;

    public MountainbikeService(MountainbikeRepository mountainbikeRepository, MountainbikeMapper mountainbikeMapper) {
        this.mountainbikeRepository = mountainbikeRepository;
        this.mountainbikeMapper = mountainbikeMapper;
    }

    public List<MountainbikeDto> getAllMountainbikes() {
        List<Mountainbike> mtbList = mountainbikeRepository.findAll();
        List<MountainbikeDto> mtbDtoList = new ArrayList<>();

        for (Mountainbike mtb : mtbList) {
            MountainbikeDto dto = mountainbikeMapper.transferToDto(mtb);
            mtbDtoList.add(dto);
        }
        return mtbDtoList;
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
}
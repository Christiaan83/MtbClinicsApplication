package nl.edemtb.mtbclinicsapplication.mappers;

import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeInputDto;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import org.springframework.stereotype.Component;

@Component
public class MountainbikeMapper {

    public Mountainbike transferToMountainbike(MountainbikeInputDto dto) {
        if (dto == null) {
            return null;
        }
        var mountainbike = new Mountainbike();
        mountainbike.setName(dto.getName());
        mountainbike.setWheelSize(dto.getWheelSize());
        mountainbike.setFrameSize(dto.getFrameSize());
        mountainbike.setGears(dto.getGears());
        mountainbike.setPricePerDayPart(dto.getPricePerDayPart());
        mountainbike.setAmount(dto.getAmount());
        mountainbike.setForAdult(dto.getForAdult());
        mountainbike.setFullSuspension(dto.getFullSuspension());
        mountainbike.setAvailable(dto.getAvailable());
        return mountainbike;
    }

    public MountainbikeDto transferToDto(Mountainbike mountainbike) {
        if (mountainbike == null) {
            return null;
        }

        MountainbikeDto dto = new MountainbikeDto();
        dto.setId(mountainbike.getId());
        dto.setName(mountainbike.getName());
        dto.setWheelSize(mountainbike.getWheelSize());
        dto.setFrameSize(mountainbike.getFrameSize());
        dto.setGears(mountainbike.getGears());
        dto.setPricePerDayPart(mountainbike.getPricePerDayPart());
        dto.setAmount(mountainbike.getAmount());
        dto.setForAdult(mountainbike.getForAdult());
        dto.setFullSuspension(mountainbike.getFullSuspension());
        dto.setAvailable(mountainbike.getAvailable());
        dto.setPicture(mountainbike.getPicture());
        return dto;
    }
}

package nl.edemtb.mtbclinicsapplication.mappers;

import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.dtos.rental.RentalDto;
import nl.edemtb.mtbclinicsapplication.dtos.rental.RentalInputDto;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.models.Rental;
import nl.edemtb.mtbclinicsapplication.repositories.RentalRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentalMapper {

    private final RentalRepository rentalRepository;
    private final MountainbikeMapper mountainbikeMapper;
    private final UnregisteredUserMapper unregisteredUserMapper;


    public RentalMapper(RentalRepository rentalRepository, MountainbikeMapper mountainbikeMapper, UnregisteredUserMapper unregisteredUserMapper) {
        this.rentalRepository = rentalRepository;
        this.mountainbikeMapper = mountainbikeMapper;
        this.unregisteredUserMapper = unregisteredUserMapper;
    }

    public Rental transferToRental(RentalInputDto dto) {
        if(dto == null) return null;

        Rental rental = new Rental();
        rental.setStartTime(dto.getStartTime());
        rental.setStartDate(dto.getStartDate());
        rental.setEndDateTime(dto.getEndDateTime());
        rental.setRentingWholeDay(dto.getRentingWholeDay());
        return rental;
    }

    public RentalDto transferToRentalDto(Rental rental) {
        if(rental == null) return null;

        RentalDto dto = new RentalDto();
        dto.setId(rental.getId());
        dto.setStartTime(rental.getStartTime());
        dto.setStartDate(rental.getStartDate());
        dto.setEndDateTime(rental.getEndDateTime());
        dto.setRentingWholeDay(rental.getRentingWholeDay());

        return dto;
    }

    public List<RentalDto> transferRentalListToRentalDto(List<Rental> rentals) {
        List<RentalDto> rentalDtoList = new ArrayList<>();

        for(Rental rental : rentals) {
            rentalDtoList.add(transferToRentalDto(rental));
        }
        return rentalDtoList;
    }

    public RentalDto rentalInputMapper(Long id,RentalInputDto inputDto) {

        rentalRepository.findById(id);
        Rental rental = rentalRepository.findById(id).get();

        if (inputDto.getStartTime() != null) {
            rental.setStartTime(inputDto.getStartTime());
        }
        if (inputDto.getStartDate() != null) {
            rental.setStartDate(inputDto.getStartDate());
        }
        if (inputDto.getEndDateTime() != null) {
            rental.setEndDateTime(inputDto.getEndDateTime());
        }
        if (inputDto.getRentingWholeDay() != null) {
            rental.setRentingWholeDay(inputDto.getRentingWholeDay());
        }
        rentalRepository.save(rental);
        return transferToRentalDto(rental);
    }
}

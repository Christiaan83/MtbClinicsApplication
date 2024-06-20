package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.rental.RentalDto;
import nl.edemtb.mtbclinicsapplication.dtos.rental.RentalInputDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.RentalMapper;
import nl.edemtb.mtbclinicsapplication.models.Rental;
import nl.edemtb.mtbclinicsapplication.repositories.MountainbikeRepository;
import nl.edemtb.mtbclinicsapplication.repositories.RentalRepository;
import nl.edemtb.mtbclinicsapplication.repositories.UnregisteredUserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final MountainbikeRepository mountainbikeRepository;
    private final UnregisteredUserRepository unregisteredUserRepository;
    private final RentalMapper rentalMapper;


    public RentalService(RentalRepository rentalRepository, MountainbikeRepository mountainbikeRepository, UnregisteredUserRepository unregisteredUserRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.mountainbikeRepository = mountainbikeRepository;
        this.unregisteredUserRepository = unregisteredUserRepository;
        this.rentalMapper = rentalMapper;
    }

    public List<RentalDto> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentalMapper.transferRentalListToRentalDto(rentals);
    }

    public RentalDto getRentalById(Long id) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);

        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            return rentalMapper.transferToRentalDto(rental);
        } else {
            throw new RecordNotFoundException("Rental not found");
        }
    }

    public RentalDto addRental(RentalInputDto dto) {

        Rental rental = rentalMapper.transferToRental(dto);
        rentalRepository.save(rental);

        return rentalMapper.transferToRentalDto(rental);
    }

    public RentalDto updateRental(Long id, RentalInputDto dto) {

        if (!rentalRepository.existsById(id)) {
            throw new RecordNotFoundException("Rental not found");
        } else {
            return rentalMapper.rentalInputMapper(id, dto);
        }
    }

    public void deleteRental(Long id) {
        if (rentalRepository.findById(id).isPresent()) {
            rentalRepository.deleteById(id);
        }
    }

    public Rental assignMtbAndUnregisteredUserToRental(Long id, Long mountainbikeId, Long unregisteredUserId) {

        var optionalRental = rentalRepository.findById(id);
        var optionalMountainbike = mountainbikeRepository.findById(mountainbikeId);
        var optionalUnregisteredUser = unregisteredUserRepository.findById(unregisteredUserId);

        if (optionalRental.isPresent() && optionalMountainbike.isPresent() && optionalUnregisteredUser.isPresent()) {
            var rental = optionalRental.get();
            var mountainbike = optionalMountainbike.get();
            var unregisteredUser = optionalUnregisteredUser.get();

            rental.setMountainbike(mountainbike);
            rental.setUnregisteredUser(unregisteredUser);

            int currentAmount = mountainbike.getAmount();
            mountainbike.setAmount(currentAmount - 1);

            return rentalRepository.save(rental);
        } else {
            throw new RecordNotFoundException();
        }

    }

}

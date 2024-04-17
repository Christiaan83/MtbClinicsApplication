package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.rental.RentalDto;
import nl.edemtb.mtbclinicsapplication.dtos.rental.RentalInputDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.RentalMapper;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.models.Rental;
import nl.edemtb.mtbclinicsapplication.models.UnregisteredUser;
import nl.edemtb.mtbclinicsapplication.repositories.MountainbikeRepository;
import nl.edemtb.mtbclinicsapplication.repositories.RentalRepository;
import nl.edemtb.mtbclinicsapplication.repositories.UnregisteredUserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final MountainbikeRepository mountainbikeRepository;
    private final UnregisteredUserRepository unregisteredUserRepository;
    private final MountainbikeService mountainbikeService;

    public RentalService(RentalRepository rentalRepository, RentalMapper rentalMapper, MountainbikeRepository mountainbikeRepository, UnregisteredUserRepository unregisteredUserRepository, MountainbikeService mountainbikeService) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.mountainbikeRepository = mountainbikeRepository;
        this.unregisteredUserRepository = unregisteredUserRepository;
        this.mountainbikeService = mountainbikeService;
    }

    public List<RentalDto> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentalMapper.transferRentalListToRentalDto(rentals);
    }

    public RentalDto getRentalById(Long id) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);

        if(rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            return rentalMapper.transferToRentalDto(rental);
        }else {
            throw new RecordNotFoundException("Rental niet gevonden");
        }
    }

    public RentalDto addRental(RentalInputDto dto) {

        Rental rental = rentalMapper.transferToRental(dto);
        rentalRepository.save(rental);

        return rentalMapper.transferToRentalDto(rental);
    }

    public RentalDto updateRental(Long id, RentalInputDto dto) {

        if(!rentalRepository.existsById(id)) {
            throw new RecordNotFoundException("Rental niet gevonden");
        }else {
            return rentalMapper.rentalInputMapper(id, dto);
        }
    }
    public void deleteRental(Long id) {
        if(rentalRepository.findById(id).isPresent()) {
            rentalRepository.deleteById(id);
        }
    }
    public Rental createRental(RentalDto rentalDTO) {
        UnregisteredUser unregisteredUser = unregisteredUserRepository.findById(rentalDTO.getUnregisteredUserId()).orElseThrow();
        Set<Mountainbike> mountainbikes = new HashSet<>();
        for (Long mountainbikeId : rentalDTO.getMountainbikeIds()) {
            Mountainbike mountainbike = mountainbikeRepository.findById(mountainbikeId).orElseThrow();
            mountainbikes.add(mountainbike);
        }

        Rental rental = new Rental();
        rental.setUnregisteredUser(unregisteredUser);
        rental.setMountainbikes(mountainbikes);
        return rentalRepository.save(rental);
    }

}

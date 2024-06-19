package nl.edemtb.mtbclinicsapplication.controllers;

import jakarta.validation.Valid;
import nl.edemtb.mtbclinicsapplication.dtos.rental.RentalDto;
import nl.edemtb.mtbclinicsapplication.dtos.rental.RentalInputDto;
import nl.edemtb.mtbclinicsapplication.models.Rental;
import nl.edemtb.mtbclinicsapplication.models.UnregisteredUser;
import nl.edemtb.mtbclinicsapplication.services.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RequestMapping("/rentals")
@RestController
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping()
    public ResponseEntity<List<RentalDto>> getAllRentals() {

        List<RentalDto> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable Long id) {

        RentalDto rental = rentalService.getRentalById(id);
        return ResponseEntity.ok(rental);
    }

    @PostMapping()
    public ResponseEntity<Object> addRental(@Valid @RequestBody RentalInputDto inputDto) {

        RentalDto dto = rentalService.addRental(inputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> updateRental(@PathVariable("id") Long id, @RequestBody RentalInputDto updateRental) {

        RentalDto dto = rentalService.updateRental(id, updateRental);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRental(@PathVariable("id") Long id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/mountainbike/{mtbId}/user/{userId}")
    public ResponseEntity<Rental> assignMountainBikeAndUnregisteredUserToRental(
            @PathVariable("id") Long id,
            @PathVariable("mtbId") Long mtbId,
            @PathVariable("userId") Long userId) {

        Rental updateRental = rentalService.assignMtbAndUnregisteredUserToRental(id, mtbId, userId);
        return ResponseEntity.ok().body(updateRental);
    }

  }
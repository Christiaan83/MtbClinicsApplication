package nl.edemtb.mtbclinicsapplication.controllers;

import jakarta.validation.Valid;
import nl.edemtb.mtbclinicsapplication.dtos.BookingDto;
import nl.edemtb.mtbclinicsapplication.models.Booking;
import nl.edemtb.mtbclinicsapplication.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RequestMapping("/bookings")
@RestController
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {

        List<BookingDto> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable("id") Long id) {

        BookingDto booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<Collection<BookingDto>> getBookingsByUsername(@PathVariable("username") String username) {
        Collection<BookingDto> bookings = bookingService.getBookingsByUsername(username);
        return ResponseEntity.ok(bookings);
    }

    @PostMapping()
    public ResponseEntity<Object> createBooking(@Valid @RequestBody BookingDto bookingDto) {

        BookingDto dto = bookingService.createBooking(bookingDto);
        return ResponseEntity.created(null).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable("id") Long id, @RequestBody BookingDto bookingDto) {

        BookingDto dto = bookingService.updateBooking(id, bookingDto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/training/{trainingId}/user/{username}")

    public ResponseEntity<Booking> assignTrainingAndUserToBooking(
            @PathVariable("id") Long id,
            @PathVariable("trainingId") Long trainingId,
            @PathVariable("username") String username) {

        Booking updatedBooking = bookingService.assignTrainingAndUserToBooking(id, trainingId, username);
        return ResponseEntity.ok().body(updatedBooking);
    }
}

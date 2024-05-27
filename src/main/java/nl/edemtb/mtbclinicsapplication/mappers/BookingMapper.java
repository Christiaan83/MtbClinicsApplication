package nl.edemtb.mtbclinicsapplication.mappers;

import nl.edemtb.mtbclinicsapplication.dtos.BookingDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.models.Booking;
import nl.edemtb.mtbclinicsapplication.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class BookingMapper {


    private final BookingRepository bookingRepository;

    public BookingMapper(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public BookingDto transferToBookingDto(Booking booking) {
        if (booking == null) return null;

        BookingDto dto = new BookingDto();
        dto.setBookingDate(booking.getBookingDate());

        return dto;
    }

    public Booking transferToBooking(BookingDto bookingDto) {

        Booking booking = new Booking();
        booking.setBookingDate(bookingDto.getBookingDate());

        return booking;

    }

    public BookingDto inputMapper(Long id, BookingDto bookingDto) {

        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setBookingDate(bookingDto.getBookingDate());

            Booking savedBooking = bookingRepository.save(booking);
            return transferToBookingDto(savedBooking);
        } else {
            throw new RecordNotFoundException("Booking with id " + id + " not found");
        }
    }
}


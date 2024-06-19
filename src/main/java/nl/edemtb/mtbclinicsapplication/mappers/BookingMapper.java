package nl.edemtb.mtbclinicsapplication.mappers;

import nl.edemtb.mtbclinicsapplication.dtos.BookingDto;
import nl.edemtb.mtbclinicsapplication.dtos.RegisteredUserDto;
import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.models.Booking;
import nl.edemtb.mtbclinicsapplication.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class BookingMapper {


    private final BookingRepository bookingRepository;
    private final TrainingMapper trainingMapper;
    private final RegisteredUserMapper registeredUserMapper;

    public BookingMapper(BookingRepository bookingRepository, TrainingMapper trainingMapper, RegisteredUserMapper registeredUserMapper) {
        this.bookingRepository = bookingRepository;
        this.trainingMapper = trainingMapper;
        this.registeredUserMapper = registeredUserMapper;
    }

    public Booking transferToBooking(BookingDto bookingDto) {
        if (bookingDto == null) return null;

        Booking booking = new Booking();
        booking.setBookingDate(bookingDto.getBookingDate());
        booking.setMessage(bookingDto.getMessage());

        return booking;

    }

    public BookingDto transferToBookingDto(Booking booking) {
        if (booking == null) return null;

        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setBookingDate(booking.getBookingDate());
        dto.setMessage(booking.getMessage());

        if (booking.getTraining() != null) {
            dto.setTrainingDto(trainingMapper.transferToDto(booking.getTraining()));
        }
        if (booking.getUser() != null) {
            dto.setUserDto(registeredUserMapper.fromRegisteredUser(booking.getUser()));
        }

        return dto;
    }

    public List<BookingDto> transferToBookingDtoList(List<Booking> bookings) {
        if (bookings == null) return null;
        List<BookingDto> dtos = new ArrayList<>();

        for (Booking booking : bookings) {
            dtos.add(transferToBookingDto(booking));
        }
        return dtos;
    }

    public BookingDto bookingInputMapper(Long id, BookingDto bookingDto) {

        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            if (bookingDto.getBookingDate() != null) {
                booking.setBookingDate(bookingDto.getBookingDate());
            }
            if (bookingDto.getMessage() != null) {
                booking.setMessage(bookingDto.getMessage());
            }
            Booking savedBooking = bookingRepository.save(booking);
            return transferToBookingDto(savedBooking);
        } else {
            throw new RecordNotFoundException("Booking with id " + id + " not found");
        }
    }

}


package nl.edemtb.mtbclinicsapplication.services;

import jakarta.transaction.Transactional;
import nl.edemtb.mtbclinicsapplication.dtos.BookingDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.BookingMapper;
import nl.edemtb.mtbclinicsapplication.models.Booking;
import nl.edemtb.mtbclinicsapplication.repositories.BookingRepository;
import nl.edemtb.mtbclinicsapplication.repositories.RegisteredUserRepository;
import nl.edemtb.mtbclinicsapplication.repositories.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final TrainingRepository trainingRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, TrainingRepository trainingRepository, RegisteredUserRepository registeredUserRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.trainingRepository = trainingRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.bookingMapper = bookingMapper;
    }

    public List<BookingDto> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookingMapper.transferToBookingDtoList(bookings);
    }

    public BookingDto getBookingById(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);

        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            return bookingMapper.transferToBookingDto(booking);
        } else {
            throw new RecordNotFoundException("Booking not found");
        }
    }

    public BookingDto createBooking(BookingDto bookingDto) {

        Booking booking = bookingMapper.transferToBooking(bookingDto);
        bookingRepository.save(booking);
        return bookingMapper.transferToBookingDto(booking);
    }

    public BookingDto updateBooking(Long id, BookingDto bookingDto) {

        if (!bookingRepository.existsById(id)) {
            throw new RecordNotFoundException("Booking not found");
        } else {
            return bookingMapper.bookingInputMapper(id, bookingDto);
        }
    }

    public void deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        }
    }

    public Booking assignTrainingAndUserToBooking(Long id, Long trainingId, String username) {

        var optionalBooking = bookingRepository.findById(id);
        var optionalTraining = trainingRepository.findById(trainingId);
        var optionalUser = registeredUserRepository.findById(username);

        if (optionalBooking.isPresent() && optionalTraining.isPresent() && optionalUser.isPresent()) {
            var booking = optionalBooking.get();
            var training = optionalTraining.get();
            var user = optionalUser.get();

            booking.setTraining(training);
            booking.setUser(user);
            return bookingRepository.save(booking);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Transactional()
    public Collection<BookingDto> getBookingsByUsername(String username) {
        Set<BookingDto> bookingDtos = new HashSet<>();
        List<Booking> bookings = bookingRepository.findAllByUser_username(username);

        for (Booking booking : bookings) {
            bookingDtos.add(bookingMapper.transferToBookingDto(booking));
        }

        return bookingDtos;
    }

}
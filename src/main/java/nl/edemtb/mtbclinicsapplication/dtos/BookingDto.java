package nl.edemtb.mtbclinicsapplication.dtos;

import jakarta.validation.constraints.NotNull;
import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingDto;

import java.time.LocalDate;

public class BookingDto {
    @NotNull(message = "bookingDate is required")
    private LocalDate bookingDate;

    private TrainingDto trainingDto;
    private RegisteredUserDto  userDto;

    public TrainingDto getTrainingDto() {
        return trainingDto;
    }

    public void setTrainingDto(TrainingDto trainingDto) {
        this.trainingDto = trainingDto;
    }

    public RegisteredUserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(RegisteredUserDto userDto) {
        this.userDto = userDto;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}

package nl.edemtb.mtbclinicsapplication.dtos.rental;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RentalInputDto {

    @NotNull(message = "startDate is required")
    LocalDate startDate;
    @NotNull(message = "startTime is required")
    LocalTime startTime;
    @NotNull(message = "endDateTime is required")
    LocalTime endTime;
    Boolean rentingWholeDay;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Boolean getRentingWholeDay() {
        return rentingWholeDay;
    }

    public void setRentingWholeDay(Boolean rentingWholeDay) {
        this.rentingWholeDay = rentingWholeDay;
    }
}

package nl.edemtb.mtbclinicsapplication.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RentalDto {

    private Long id;
    LocalDate startDate;
    LocalTime startTime;
    LocalDateTime endDateTime;
    Boolean rentingWholeDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Boolean getRentingWholeDay() {
        return rentingWholeDay;
    }

    public void setRentingWholeDay(Boolean rentingWholeDay) {
        this.rentingWholeDay = rentingWholeDay;
    }
}

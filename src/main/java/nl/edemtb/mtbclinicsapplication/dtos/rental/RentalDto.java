package nl.edemtb.mtbclinicsapplication.dtos.rental;

import nl.edemtb.mtbclinicsapplication.dtos.UnregisteredUserDto;
import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.models.UnregisteredUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class RentalDto {

    private Long id;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDateTime endDateTime;
    private Boolean rentingWholeDay;

    private MountainbikeDto mountainbikeDto;
    private UnregisteredUserDto unregisteredUserDto;

    public UnregisteredUserDto getUnregisteredUserDto() {
        return unregisteredUserDto;
    }

    public void setUnregisteredUserDto(UnregisteredUserDto unregisteredUserDto) {
        this.unregisteredUserDto = unregisteredUserDto;
    }

    public MountainbikeDto getMountainbikeDto() {
        return mountainbikeDto;
    }

    public void setMountainbikeDto(MountainbikeDto mountainbikeDto) {
        this.mountainbikeDto = mountainbikeDto;
    }

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

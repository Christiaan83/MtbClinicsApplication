package nl.edemtb.mtbclinicsapplication.dtos.rental;

import nl.edemtb.mtbclinicsapplication.dtos.UnregisteredUserDto;
import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeDto;

import java.time.LocalDate;
import java.time.LocalTime;

public class RentalDto {

    private Long id;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;
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

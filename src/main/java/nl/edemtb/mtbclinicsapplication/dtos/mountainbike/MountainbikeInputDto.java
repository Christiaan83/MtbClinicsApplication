package nl.edemtb.mtbclinicsapplication.dtos.mountainbike;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

public class MountainbikeInputDto {
    @NotBlank
    @Size(max = 20, message = "Name must be between 0-20 characters")
    private String name;
    @NotBlank(message = "Wheelsize is required")
    private String wheelSize;
    @NotBlank(message = "Framesize is required")
    private String frameSize;
    @NotNull
    @Range(min = 8, max = 12)
    private Integer gears;
    @NotNull
    private Integer pricePerDayPart;
    @PositiveOrZero(message = "Mountainbike can not have a negative amount.")
    private Integer amount;
    private Boolean forAdult;
    private Boolean fullSuspension;
    private Boolean available;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPricePerDayPart() {
        return pricePerDayPart;
    }

    public void setPricePerDayPart(Integer pricePerDayPart) {
        this.pricePerDayPart = pricePerDayPart;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(String wheelSize) {
        this.wheelSize = wheelSize;
    }

    public String getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(String frameSize) {
        this.frameSize = frameSize;
    }

    public Integer getGears() {
        return gears;
    }

    public void setGears(Integer gears) {
        this.gears = gears;
    }

    public Boolean getForAdult() {
        return forAdult;
    }

    public void setForAdult(Boolean forAdult) {
        this.forAdult = forAdult;
    }

    public Boolean getFullSuspension() {
        return fullSuspension;
    }

    public void setFullSuspension(Boolean fullSuspension) {
        this.fullSuspension = fullSuspension;
    }
}


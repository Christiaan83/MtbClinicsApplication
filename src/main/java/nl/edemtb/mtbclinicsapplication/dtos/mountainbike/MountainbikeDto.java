package nl.edemtb.mtbclinicsapplication.dtos.mountainbike;

import nl.edemtb.mtbclinicsapplication.models.Picture;


public class MountainbikeDto {
    private Long id;

    private String name;
    private String wheelSize;
    private String frameSize;
    private Integer gears;
    private Integer pricePerDayPart;
    private Integer amount;
    private Boolean forAdult;
    private Boolean fullSuspension;
    private Boolean available;
    private Picture picture;



    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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


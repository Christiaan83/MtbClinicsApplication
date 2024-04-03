package nl.edemtb.mtbclinicsapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mountainbikes")

public class Mountainbike {

    @Id
    @GeneratedValue
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

    public Mountainbike(){
    }
    public Mountainbike(Long id, String name, String wheelSize, String frameSize, Integer gears, Integer amount, Integer pricePerDayPart, Boolean forAdult, Boolean fullSuspension, Boolean available) {
        this.id = id;
        this.name = name;
        this.wheelSize = wheelSize;
        this.frameSize = frameSize;
        this.gears = gears;
        this.amount = amount;
        this.pricePerDayPart = pricePerDayPart;
        this.forAdult = forAdult;
        this.fullSuspension = fullSuspension;
        this.available = available;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

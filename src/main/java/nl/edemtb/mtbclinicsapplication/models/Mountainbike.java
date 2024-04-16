package nl.edemtb.mtbclinicsapplication.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "mountainbikes")

public class Mountainbike {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "1011"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
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
    @OneToOne
    Picture picture;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rental_id")
    private Rental rental;

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
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

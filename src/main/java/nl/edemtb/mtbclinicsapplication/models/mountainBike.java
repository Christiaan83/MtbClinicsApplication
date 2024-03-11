package nl.edemtb.mtbclinicsapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mountainbikes")
public class mountainBike {

    @Id
    @GeneratedValue
    private Long id;

    private String expertNiveau;
    private String wheelSize;
    private String frameSize;
    private Integer gears;
    private Boolean forAdult;
    private Boolean frontSuspension;
    private Boolean fullSuspension;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpertNiveau() {
        return expertNiveau;
    }

    public void setExpertNiveau(String expertNiveau) {
        this.expertNiveau = expertNiveau;
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

    public Boolean getFrontSuspension() {
        return frontSuspension;
    }

    public void setFrontSuspension(Boolean frontSuspension) {
        this.frontSuspension = frontSuspension;
    }

    public Boolean getFullSuspension() {
        return fullSuspension;
    }

    public void setFullSuspension(Boolean fullSuspension) {
        this.fullSuspension = fullSuspension;
    }
}

package nl.edemtb.mtbclinicsapplication.dtos.training;


import nl.edemtb.mtbclinicsapplication.enums.Difficulty;
import nl.edemtb.mtbclinicsapplication.models.Picture;

import java.time.LocalTime;

public class TrainingDto {

    private Long id;
    private String name;
    private Difficulty difficulty;
    private String location;
    private String description;
    private Double price;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean trainingInGroup;
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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Boolean getTrainingInGroup() {
        return trainingInGroup;
    }

    public void setTrainingInGroup(Boolean trainingInGroup) {
        this.trainingInGroup = trainingInGroup;
    }
}

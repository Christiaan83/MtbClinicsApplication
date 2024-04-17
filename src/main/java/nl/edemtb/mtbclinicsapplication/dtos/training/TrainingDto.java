package nl.edemtb.mtbclinicsapplication.dtos.training;


import nl.edemtb.mtbclinicsapplication.enums.Difficulty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TrainingDto {

    private Long id;
    private String name;
    private Difficulty difficulty;
    private String location;
    private String description;
    private Double price;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<LocalDate> dateList;
    private Boolean trainingInGroup;

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

    public List<LocalDate> getDateList() {
        return dateList;
    }

    public void setDateList(List<LocalDate> dateList) {
        this.dateList = dateList;
    }

    public Boolean getTrainingInGroup() {
        return trainingInGroup;
    }

    public void setTrainingInGroup(Boolean trainingInGroup) {
        this.trainingInGroup = trainingInGroup;
    }
}

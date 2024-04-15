package nl.edemtb.mtbclinicsapplication.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import nl.edemtb.mtbclinicsapplication.enums.Difficulty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TrainingInputDto {

    @NotBlank
    @Size(min = 2, max = 30, message = "Name must be between 2-30 characters")
    private String name;
    @NotNull(message = "difficulty is required")
    private Difficulty difficulty;
    @NotBlank(message = "location is required")
    private String location;
    @NotBlank(message = "description is required")
    private String description;
    @NotNull (message = "price is required")
    private Double price;
    @NotNull (message = "startTime is required")
    private LocalTime startTime;
    @NotNull (message = "endTime is required")
    private LocalTime endTime;
    @NotNull (message = "List of dates is required")
    private List<LocalDate> dateList;
    private Boolean trainingInGroup;

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

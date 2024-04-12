package nl.edemtb.mtbclinicsapplication.models;


import jakarta.persistence.*;

import nl.edemtb.mtbclinicsapplication.enums.Difficulty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "trainings")
public class Training {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@Parameter(name = "sequence_training", value = "training_sequence"),
                    @Parameter(name = "initial_value", value = "1008"),
                    @Parameter(name = "increment_size", value = "1")
            }
            )

    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private String location;
    @Column(length = Integer.MAX_VALUE)
    private String description;
    private Double price;
    private LocalTime startTime;
    private LocalTime endTime;
    @ElementCollection
    @Column(name = "date")
    private List<LocalDate> dateList;
    private boolean trainingInGroup;

    public boolean isTrainingInGroup() {
        return trainingInGroup;
    }

    public void setTrainingInGroup(boolean trainingInGroup) {
        this.trainingInGroup = trainingInGroup;
    }

    public List<LocalDate> getDateList() {
        return dateList;
    }

    public void setDateList(List<LocalDate> dateList) {
        this.dateList = dateList;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

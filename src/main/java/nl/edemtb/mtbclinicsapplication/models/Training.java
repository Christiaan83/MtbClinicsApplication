package nl.edemtb.mtbclinicsapplication.models;


import jakarta.persistence.*;

import nl.edemtb.mtbclinicsapplication.enums.Difficulty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "trainings")
public class Training {

    @Id
    @GeneratedValue(generator = "sequence-generator2")
    @GenericGenerator(name = "sequence-generator2",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@Parameter(name = "sequence_training", value = "training_sequence"),
                    @Parameter(name = "initial_value", value = "1005"),
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
    private Boolean trainingInGroup;
    @OneToOne
    Picture picture;

    @OneToMany(mappedBy = "training")
    private List<Booking> bookings;

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
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

    public Boolean getTrainingInGroup() {
        return trainingInGroup;
    }

    public void setTrainingInGroup(Boolean trainingInGroup) {
        this.trainingInGroup = trainingInGroup;
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

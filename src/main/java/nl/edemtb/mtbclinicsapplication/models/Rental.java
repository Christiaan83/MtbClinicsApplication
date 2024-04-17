package nl.edemtb.mtbclinicsapplication.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(generator = "sequence-generator3")
    @GenericGenerator(name = "sequence-generator3",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@Parameter(name = "sequence_rental", value = "route_rental"),
                    @Parameter(name = "initial_value", value = "1003"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDateTime endDateTime;
    private Boolean rentingWholeDay;

    @ManyToMany
    @JoinTable(name = "rental_mountainbikes",
    joinColumns = @JoinColumn(name = "rental_id"),
    inverseJoinColumns =@JoinColumn(name = "mountainbike_id"))
    private Set<Mountainbike> mountainbikes;

    @ManyToOne
    @JoinColumn( name = "unregistered_user_id")
    private UnregisteredUser unregisteredUser;

    public Set<Mountainbike> getMountainbikes() {
        return mountainbikes;
    }

    public void setMountainbikes(Set<Mountainbike> mountainbikes) {
        this.mountainbikes = mountainbikes;
    }

    public UnregisteredUser getUnregisteredUser() {
        return unregisteredUser;
    }

    public void setUnregisteredUser(UnregisteredUser unregisteredUser) {
        this.unregisteredUser = unregisteredUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Boolean getRentingWholeDay() {
        return rentingWholeDay;
    }

    public void setRentingWholeDay(Boolean rentingWholeDay) {
        this.rentingWholeDay = rentingWholeDay;
    }
}

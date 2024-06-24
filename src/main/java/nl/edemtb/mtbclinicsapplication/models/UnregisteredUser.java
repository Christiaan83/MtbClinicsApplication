package nl.edemtb.mtbclinicsapplication.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "unregistered_users")
public class UnregisteredUser extends BasicUser {
    @Id
    @GeneratedValue(generator = "sequence-generator4")
    @GenericGenerator(name = "sequence-generator4",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_user", value = "route_user"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1002"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @OneToMany(mappedBy = "unregisteredUser")
    private List<Rental> rentals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}

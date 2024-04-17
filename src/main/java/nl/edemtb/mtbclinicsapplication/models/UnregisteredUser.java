package nl.edemtb.mtbclinicsapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "unregistered_users")
public class UnregisteredUser extends BasicUser{

    @OneToMany(mappedBy = "unregisteredUser")
    private List<Rental> rentals;

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}

package nl.edemtb.mtbclinicsapplication.repositories;

import nl.edemtb.mtbclinicsapplication.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {

}

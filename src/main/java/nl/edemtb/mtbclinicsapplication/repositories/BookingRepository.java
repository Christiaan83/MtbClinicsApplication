package nl.edemtb.mtbclinicsapplication.repositories;

import nl.edemtb.mtbclinicsapplication.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

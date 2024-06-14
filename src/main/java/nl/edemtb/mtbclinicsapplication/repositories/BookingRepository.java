package nl.edemtb.mtbclinicsapplication.repositories;

import nl.edemtb.mtbclinicsapplication.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUser_username(String username);
}

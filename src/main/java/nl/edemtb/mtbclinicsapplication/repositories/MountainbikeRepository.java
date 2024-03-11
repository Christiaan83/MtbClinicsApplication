package nl.edemtb.mtbclinicsapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
public interface MountainbikeRepository extends JpaRepository<Mountainbike, Long> {
}

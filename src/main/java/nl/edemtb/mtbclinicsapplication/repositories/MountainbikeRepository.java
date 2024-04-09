package nl.edemtb.mtbclinicsapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;

import java.util.List;

public interface MountainbikeRepository extends JpaRepository<Mountainbike, Long> {
    List<Mountainbike> findAllMountainbikesByFrameSizeEqualsIgnoreCase(String frameSize);

    List<Mountainbike> findByForAdultIsTrue();

    List<Mountainbike> findByForAdultIsFalse();
}
package nl.edemtb.mtbclinicsapplication.repositories;

import nl.edemtb.mtbclinicsapplication.enums.Difficulty;
import nl.edemtb.mtbclinicsapplication.enums.RouteType;
import nl.edemtb.mtbclinicsapplication.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MountainbikeRepository extends JpaRepository<Mountainbike, Long> {
    List<Mountainbike> findAllMountainbikesByFrameSizeEqualsIgnoreCase(String frameSize);


    @Query("select m from Mountainbike m " +

            "where (lower(:frameSize) is null or lower(m.frameSize) = lower(:frameSize))" +
            "and (:forAdult is null or m.forAdult= :forAdult)"+
            "and (:fullSuspension is null or m.fullSuspension = :fullSuspension)"
            )
    List<Mountainbike> searchBySizeAndForAdult(
            String frameSize,
            Boolean forAdult,
            Boolean fullSuspension);
}
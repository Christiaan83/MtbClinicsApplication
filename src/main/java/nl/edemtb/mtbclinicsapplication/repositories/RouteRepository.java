package nl.edemtb.mtbclinicsapplication.repositories;

import nl.edemtb.mtbclinicsapplication.enums.RouteType;
import nl.edemtb.mtbclinicsapplication.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteRepository extends JpaRepository <Route, Long>{

    List<Route> findAllRoutesByPlaceEqualsIgnoreCaseAndAvailable(String place, boolean available);

    @Query("select r from Route r " +
            "where (:place is null or r.place = :place)" +
            "and (:routeType is null or r.routeType = :routeType)" +
            "and (:difficulty is null or r.difficulty = :difficulty)" +
            "and r.available")
    List<Route> findAvailableRoutes(
            String place,
            RouteType routeType,
            String difficulty);
}

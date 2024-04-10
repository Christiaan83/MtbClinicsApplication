package nl.edemtb.mtbclinicsapplication.repositories;

import nl.edemtb.mtbclinicsapplication.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository <Route, Long>{

    List<Route> findAllRoutesByPlaceEqualsIgnoreCaseAndAvailable(String place, Boolean available);
    List<Route> findRoutesByRouteTypeAndAvailable(String routeType, Boolean available);
    List<Route> findRoutesByDifficultyAndAvailable(String difficulty, Boolean available);
}

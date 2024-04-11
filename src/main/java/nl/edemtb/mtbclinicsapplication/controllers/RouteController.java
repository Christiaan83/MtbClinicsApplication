package nl.edemtb.mtbclinicsapplication.controllers;

import nl.edemtb.mtbclinicsapplication.dtos.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.dtos.RouteDto;
import nl.edemtb.mtbclinicsapplication.enums.RouteType;
import nl.edemtb.mtbclinicsapplication.services.RoutesService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/routes")
@RestController
public class RouteController {

    private final RoutesService routesService;

    List<RouteDto> routes;

    public RouteController(RoutesService routesService) {
        this.routesService = routesService;
    }

    @GetMapping()

    public ResponseEntity<List<RouteDto>> getAllRoutes(){
        routes = routesService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/{id}")

    public ResponseEntity<RouteDto> getRouteById(@PathVariable("id") Long id){

        RouteDto route = routesService.getRouteById(id);
        return ResponseEntity.ok().body(route);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RouteDto>> findAvailableRoutes(
            @RequestParam() @Nullable String place,
            @RequestParam() @Nullable RouteType routeType,
            @RequestParam() @Nullable String difficulty
    ) {
        routes = routesService.search(place, routeType, difficulty);
        return ResponseEntity.ok(routes);
    }
}

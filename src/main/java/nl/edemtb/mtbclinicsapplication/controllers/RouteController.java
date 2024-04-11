package nl.edemtb.mtbclinicsapplication.controllers;

import jakarta.validation.Valid;
import nl.edemtb.mtbclinicsapplication.dtos.RouteDto;
import nl.edemtb.mtbclinicsapplication.dtos.RouteInputDto;
import nl.edemtb.mtbclinicsapplication.enums.Difficulty;
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



    public RouteController(RoutesService routesService) {
        this.routesService = routesService;
    }

    @GetMapping()

    public ResponseEntity<List<RouteDto>> getAllRoutes(){
        var routes = routesService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/{id}")

    public ResponseEntity<RouteDto> getRouteById(@PathVariable("id") Long id){

        RouteDto route = routesService.getRouteById(id);
        return ResponseEntity.ok().body(route);
    }

    @GetMapping("/search/{place}")
    public ResponseEntity<List<RouteDto>> findRouteByPlace(@PathVariable("place") String place) {
        var routes = routesService.searchByPlace(place);
        return ResponseEntity.ok().body(routes);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RouteDto>> findAvailableRoutes(
            @RequestParam() @Nullable String place,
            @RequestParam() @Nullable RouteType routeType,
            @RequestParam() @Nullable Difficulty difficulty,
            @RequestParam() @Nullable String province
    ) {
        var routes = routesService.search(place, routeType, difficulty, province);
        return ResponseEntity.ok(routes);
    }
    @PostMapping()
    public ResponseEntity<Object> addRoute(@Valid @RequestBody RouteInputDto inputDto){
        var dto = routesService.addRoute(inputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRoute(@PathVariable Long id){
         routesService.deleteRoute(id);
         return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteDto> updateRoute(@PathVariable Long id, @RequestBody RouteInputDto updatedRoute) {

        RouteDto dto = routesService.updateRoute(id, updatedRoute);
        return ResponseEntity.ok().body(dto);
    }
}


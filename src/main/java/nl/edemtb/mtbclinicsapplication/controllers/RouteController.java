package nl.edemtb.mtbclinicsapplication.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import nl.edemtb.mtbclinicsapplication.dtos.route.RouteDto;
import nl.edemtb.mtbclinicsapplication.dtos.route.RouteInputDto;
import nl.edemtb.mtbclinicsapplication.enums.Difficulty;
import nl.edemtb.mtbclinicsapplication.enums.RouteType;
import nl.edemtb.mtbclinicsapplication.models.Route;
import nl.edemtb.mtbclinicsapplication.services.PictureService;
import nl.edemtb.mtbclinicsapplication.services.RoutesService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RequestMapping("/routes")
@RestController
public class RouteController {

    private final RoutesService routesService;
    private final PictureService pictureService;


    public RouteController(RoutesService routesService, PictureService pictureService) {
        this.routesService = routesService;
        this.pictureService = pictureService;

    }

    @GetMapping()

    public ResponseEntity<List<RouteDto>> getAllRoutes() {
        var routes = routesService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/{id}")

    public ResponseEntity<RouteDto> getRouteById(@PathVariable("id") Long id) {

        RouteDto route = routesService.getRouteById(id);
        return ResponseEntity.ok().body(route);
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
    public ResponseEntity<Object> addRoute(@Valid @RequestBody RouteInputDto inputDto) {
        var dto = routesService.addRoute(inputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRoute(@PathVariable Long id) {
        routesService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteDto> updateRoute(@PathVariable Long id, @RequestBody RouteInputDto updatedRoute) {

        RouteDto dto = routesService.updateRoute(id, updatedRoute);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/{id}/picture")
    public ResponseEntity<Route> addPhotoToRoute(@PathVariable("id") Long id,
                                                 @RequestBody MultipartFile file)
            throws IOException {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/routes/")
                .path(Objects.requireNonNull(id.toString()))
                .path("/picture")
                .toUriString();
        String fileName = pictureService.storePicture(file);
        Route route = routesService.assignPhotoToRoute(fileName, id);

        return ResponseEntity.created(URI.create(url)).body(route);

    }

    @GetMapping("/{id}/picture")
    public ResponseEntity<Resource> getRoutePhoto(@PathVariable("id") Long id, HttpServletRequest request) {
        Resource resource = routesService.getPictureFromRoute(id);

        String image;

        try {
            image = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {

            image = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(image))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

}


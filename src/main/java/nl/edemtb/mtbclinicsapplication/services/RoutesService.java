package nl.edemtb.mtbclinicsapplication.services;

import jakarta.transaction.Transactional;
import nl.edemtb.mtbclinicsapplication.dtos.route.RouteDto;
import nl.edemtb.mtbclinicsapplication.dtos.route.RouteInputDto;
import nl.edemtb.mtbclinicsapplication.enums.Difficulty;
import nl.edemtb.mtbclinicsapplication.enums.RouteType;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.RouteMapper;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.models.Picture;
import nl.edemtb.mtbclinicsapplication.models.Route;
import nl.edemtb.mtbclinicsapplication.repositories.PictureUploadRepository;
import nl.edemtb.mtbclinicsapplication.repositories.RouteRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RoutesService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    private final PictureService pictureService;
    private final PictureUploadRepository uploadRepository;

    public RoutesService(RouteRepository routeRepository, RouteMapper routeMapper, PictureService pictureService, PictureUploadRepository uploadRepository) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
        this.pictureService = pictureService;
        this.uploadRepository = uploadRepository;
    }

    public List<RouteDto> getAllRoutes() {
        var routeList = routeRepository.findAll();
        return routeMapper.transferRouteListToDtoList(routeList);
    }

    public RouteDto getRouteById(long id) {
        Optional<Route> routeOptional = routeRepository.findById(id);
        if (routeOptional.isPresent()) {
            Route route = routeOptional.get();
            return routeMapper.transferToDto(route);
        } else {
            throw new RecordNotFoundException("Route with id: " + id + " not found.");
        }
    }

    public List<RouteDto> searchByPlace(String place) {
        var routeList = routeRepository.findAllRoutesByPlaceEqualsIgnoreCaseAndAvailable(place, true);
        if (routeList.isEmpty()) {
            throw new RecordNotFoundException("No route found in " + place + ".");
        } else {
            return routeMapper.transferRouteListToDtoList(routeList);
        }
    }

    public List<RouteDto> search(String place, RouteType routeType, Difficulty difficulty, String province) {
        var routeList = routeRepository.findAvailableRoutes(place, routeType, difficulty, province);
        return routeMapper.transferRouteListToDtoList(routeList);
    }

    public RouteDto addRoute(RouteInputDto dto) {
        Route route = routeMapper.transferToRoute(dto);
        routeRepository.save(route);

        return routeMapper.transferToDto(route);
    }

    public void deleteRoute(@RequestBody Long id) {

        if (routeRepository.findById(id).isPresent()) {
            routeRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No route found with id: " + id);
        }
    }

    public RouteDto updateRoute(Long id, RouteInputDto inputDto) {
        if (routeRepository.findById(id).isEmpty()) {
            throw new RecordNotFoundException("No route found!");
        } else {
            return routeMapper.routeInputMapper(id, inputDto);
        }
    }

    @Transactional
    public Resource getPictureFromRoute(Long id) throws FileNotFoundException {
        Optional<Route> optionalRoute = routeRepository.findById(id);
        if (optionalRoute.isEmpty()) {
            throw new RecordNotFoundException("Route with id: " + id + " not found.");
        }
        Picture picture = optionalRoute.get().getPicture();
        if (picture == null) {
            throw new RecordNotFoundException("Route with id: " + id + " had no photo.");
        }
        return pictureService.downLoadPicture(picture.getFileName());
    }

    @Transactional
    public Route assignPhotoToRoute(String filename, Long id) {
        Optional<Route> optionalRoute = routeRepository.findById(id);
        Optional<Picture> optionalPicture = uploadRepository.findByFileName(filename);

        if (optionalRoute.isPresent() && optionalPicture.isPresent()) {
            Picture picture = optionalPicture.get();
            Route route = optionalRoute.get();
            route.setPicture(picture);
            return routeRepository.save(route);
        } else {
            throw new RecordNotFoundException("Route or picture not found.");
        }
    }
}
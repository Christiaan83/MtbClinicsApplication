package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.RouteDto;
import nl.edemtb.mtbclinicsapplication.dtos.RouteInputDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.helpers.RouteHelper;
import nl.edemtb.mtbclinicsapplication.mappers.RouteMapper;
import nl.edemtb.mtbclinicsapplication.models.Route;
import nl.edemtb.mtbclinicsapplication.repositories.RouteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class RoutesService {

    private final RouteRepository routeRepository;

    private final RouteMapper routeMapper;

    List<Route> routeList;

    public RoutesService(RouteRepository routeRepository, RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
    }

    public List<RouteDto> getAllRoutes() {
        routeList = routeRepository.findAll();
        return routeMapper.transferRouteListToDtoList(routeList);
    }

    public RouteDto getRouteById(long id) {
        Optional<Route> routeOptional = routeRepository.findById(id);
        if (routeOptional.isPresent()) {
            Route route = routeOptional.get();
            return routeMapper.transferToDto(route);
        } else {
            throw new RecordNotFoundException("Route niet met id: " + id +" niet gevonden.");
        }
    }

    public List<RouteDto> searchByPlace(String place, boolean available){
        RouteHelper.checkRouteAvailability(available);
        routeList = routeRepository.findAllRoutesByPlaceEqualsIgnoreCaseAndAvailable(place, true);
        return routeMapper.transferRouteListToDtoList(routeList);
    }

    public List<RouteDto> searchByRouteType(String routeType, boolean available) {
        RouteHelper.checkRouteAvailability(available);
        routeList = routeRepository.findRoutesByRouteTypeAndAvailable(routeType, true);
        return routeMapper.transferRouteListToDtoList(routeList);
    }

    public List<RouteDto> searchByDifficulty(String difficulty, boolean available){
        RouteHelper.checkRouteAvailability(available);
        routeList = routeRepository.findRoutesByDifficultyAndAvailable(difficulty, true);
        return routeMapper.transferRouteListToDtoList(routeList);
    }

    public RouteDto addRoute(RouteInputDto dto) {
        Route route = routeMapper.transferToRoute(dto);
        routeRepository.save(route);

        return routeMapper.transferToDto(route);
    }

    public void deleteRoute(@RequestBody Long id) {

        if (routeRepository.findById(id).isPresent()){
            routeRepository.deleteById(id);
        }else {
            throw new RecordNotFoundException("Geen route gevonden met id: " + id);
        }
    }
    public void updateRoute(Long id, RouteInputDto inputDto){
        if(routeRepository.findById(id).isEmpty()){
            throw new RecordNotFoundException("Geen route gevonden!");
        }

        routeMapper.routeInputMapper(id, inputDto);
    }
}
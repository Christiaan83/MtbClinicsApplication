package nl.edemtb.mtbclinicsapplication.mappers;

import nl.edemtb.mtbclinicsapplication.dtos.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.dtos.RouteDto;
import nl.edemtb.mtbclinicsapplication.dtos.RouteInputDto;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.models.Route;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RouteMapper {

    public Route transferToRoute(RouteInputDto dto) {
        if (dto == null) {
            return null;
        }
        var route = new Route();
        route.setName(dto.getName());
        route.setRouteType(dto.getRouteType());
        route.setDifficulty(dto.getDifficulty());
        route.setStartingPoint(dto.getStartingPoint());
        route.setPlace(dto.getPlace());
        route.setProvince(dto.getProvince());
        route.setRouteInformation(dto.getRouteInformation());
        route.setDistance(dto.getDistance());
        route.setAvailable(dto.getAvailable());
        return route;
    }

    public RouteDto transferToDto(Route route) {
        if (route == null) {
            return null;
        }
        RouteDto dto = new RouteDto();
        dto.setId(route.getId());
        dto.setName(route.getName());
        dto.setRouteType(route.getRouteType());
        dto.setDifficulty(route.getDifficulty());
        dto.setStartingPoint(route.getStartingPoint());
        dto.setPlace(route.getPlace());
        dto.setProvince(route.getProvince());
        dto.setRouteInformation(route.getRouteInformation());
        dto.setDistance(route.getDistance());
        dto.setAvailable(route.getAvailable());
        return dto;
    }

    public List<RouteDto> transferRouteListToDtoList(List<Route> routes) {
        List<RouteDto> routeDtoList = new ArrayList<>();

        for (Route route : routes) {
           RouteDto dto = transferToDto(route);
           routeDtoList.add(dto);
        }
        return routeDtoList;
    }
}

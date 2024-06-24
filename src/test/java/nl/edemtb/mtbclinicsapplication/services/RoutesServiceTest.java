package nl.edemtb.mtbclinicsapplication.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import nl.edemtb.mtbclinicsapplication.dtos.route.RouteDto;
import nl.edemtb.mtbclinicsapplication.dtos.route.RouteInputDto;
import nl.edemtb.mtbclinicsapplication.enums.Difficulty;
import nl.edemtb.mtbclinicsapplication.enums.RouteType;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.RouteMapper;
import nl.edemtb.mtbclinicsapplication.models.Picture;
import nl.edemtb.mtbclinicsapplication.models.Route;
import nl.edemtb.mtbclinicsapplication.repositories.PictureUploadRepository;
import nl.edemtb.mtbclinicsapplication.repositories.RouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;


import java.util.List;
import java.util.Optional;

public class RoutesServiceTest {

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private RouteMapper routeMapper;

    @Mock
    private PictureService pictureService;

    @Mock
    private PictureUploadRepository uploadRepository;

    @InjectMocks
    private RoutesService routesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRoutes() {
        // Arrange
        List<Route> routes = List.of(new Route());
        List<RouteDto> routeDtos = List.of(new RouteDto());

        when(routeRepository.findAll()).thenReturn(routes);
        when(routeMapper.transferRouteListToDtoList(routes)).thenReturn(routeDtos);

        // Act
        List<RouteDto> result = routesService.getAllRoutes();

        assertEquals(routeDtos, result);
        verify(routeRepository).findAll();
        verify(routeMapper).transferRouteListToDtoList(routes);
    }

    @Test
    void testGetRouteById() {
        // Arrange
        long id = 1L;
        Route route = new Route();
        RouteDto routeDto = new RouteDto();

        // Act
        when(routeRepository.findById(id)).thenReturn(Optional.of(route));
        when(routeMapper.transferToDto(route)).thenReturn(routeDto);

        RouteDto result = routesService.getRouteById(id);

        assertEquals(routeDto, result);
        verify(routeRepository).findById(id);
        verify(routeMapper).transferToDto(route);
    }

    @Test
    void testGetRouteByIdNotFound() {
        // Arrange
        long id = 1L;
        when(routeRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> routesService.getRouteById(id));
        verify(routeRepository).findById(id);
    }

    @Test
    void testSearch() {
        // Arrange
        String place = "place";
        RouteType routeType = RouteType.ADULT;
        Difficulty difficulty = Difficulty.EASY;
        String province = "province";

        List<Route> routes = List.of(new Route());
        List<RouteDto> routeDtos = List.of(new RouteDto());

        when(routeRepository.findAvailableRoutes(place, routeType, difficulty, province)).thenReturn(routes);
        when(routeMapper.transferRouteListToDtoList(routes)).thenReturn(routeDtos);

        // Act
        List<RouteDto> result = routesService.search(place, routeType, difficulty, province);

        assertEquals(routeDtos, result);
        verify(routeRepository).findAvailableRoutes(place, routeType, difficulty, province);
        verify(routeMapper).transferRouteListToDtoList(routes);
    }

    @Test
    void testAddRoute() {
        // Arrange
        RouteInputDto dto = new RouteInputDto();
        Route route = new Route();
        RouteDto routeDto = new RouteDto();

        when(routeMapper.transferToRoute(dto)).thenReturn(route);
        when(routeRepository.save(route)).thenReturn(route);
        when(routeMapper.transferToDto(route)).thenReturn(routeDto);

        // Act
        RouteDto result = routesService.addRoute(dto);

        assertEquals(routeDto, result);
        verify(routeMapper).transferToRoute(dto);
        verify(routeRepository).save(route);
        verify(routeMapper).transferToDto(route);
    }

    @Test
    void testDeleteRoute() {
        // Arrange
        long id = 1L;
        Route route = new Route();

        when(routeRepository.findById(id)).thenReturn(Optional.of(route));
        doNothing().when(routeRepository).deleteById(id);

        // Act
        routesService.deleteRoute(id);

        verify(routeRepository).findById(id);
        verify(routeRepository).deleteById(id);
    }

    @Test
    void testDeleteRouteNotFound() {
        // Arrange
        long id = 1L;

        when(routeRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> routesService.deleteRoute(id));
        verify(routeRepository).findById(id);
    }

    @Test
    void testUpdateRoute() {
        // Arrange
        long id = 1L;
        RouteInputDto dto = new RouteInputDto();
        RouteDto routeDto = new RouteDto();

        when(routeRepository.findById(id)).thenReturn(Optional.of(new Route()));
        when(routeMapper.routeInputMapper(id, dto)).thenReturn(routeDto);

        // Act
        RouteDto result = routesService.updateRoute(id, dto);

        assertEquals(routeDto, result);
        verify(routeRepository).findById(id);
        verify(routeMapper).routeInputMapper(id, dto);
    }

    @Test
    void testUpdateRouteNotFound() {
        // Arrange
        long id = 1L;
        RouteInputDto dto = new RouteInputDto();

        when(routeRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> routesService.updateRoute(id, dto));
        verify(routeRepository).findById(id);
    }

    @Test
    void testGetPictureFromRoute() {
        // Arrange
        long id = 1L;
        Route route = new Route();
        Picture picture = new Picture();
        picture.setFileName("filename");
        route.setPicture(picture);

        when(routeRepository.findById(id)).thenReturn(Optional.of(route));
        when(pictureService.downLoadPicture(picture.getFileName())).thenReturn(mock(Resource.class));

        Resource result = routesService.getPictureFromRoute(id);

        assertNotNull(result);
        verify(routeRepository).findById(id);
        verify(pictureService).downLoadPicture(picture.getFileName());
    }

    @Test
    void testGetPictureFromRouteNotFound() {
        // Arrange
        long id = 1L;

        when(routeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> routesService.getPictureFromRoute(id));
        verify(routeRepository).findById(id);
    }

    @Test
    void testGetPictureFromRouteNoPicture() {
        // Arrange
        long id = 1L;
        Route route = new Route();

        when(routeRepository.findById(id)).thenReturn(Optional.of(route));

        assertThrows(RecordNotFoundException.class, () -> routesService.getPictureFromRoute(id));
        verify(routeRepository).findById(id);
    }

    @Test
    void testAssignPhotoToRoute() {
        // Arrange
        long id = 1L;
        String filename = "filename";
        Route route = new Route();
        Picture picture = new Picture();

        when(routeRepository.findById(id)).thenReturn(Optional.of(route));
        when(uploadRepository.findByFileName(filename)).thenReturn(Optional.of(picture));
        when(routeRepository.save(any(Route.class))).thenReturn(route);

        Route result = routesService.assignPhotoToRoute(filename, id);

        assertNotNull(result);
        verify(routeRepository).findById(id);
        verify(uploadRepository).findByFileName(filename);
        verify(routeRepository).save(any(Route.class));
    }

    @Test
    void testAssignPhotoToRouteNotFound() {
        // Arrange
        long id = 1L;
        String filename = "filename";

        when(routeRepository.findById(id)).thenReturn(Optional.empty());
        when(uploadRepository.findByFileName(filename)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> routesService.assignPhotoToRoute(filename, id));
        verify(routeRepository).findById(id);
        verify(uploadRepository).findByFileName(filename);
    }
}
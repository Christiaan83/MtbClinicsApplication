package nl.edemtb.mtbclinicsapplication.controllers;

import nl.edemtb.mtbclinicsapplication.dtos.route.RouteDto;
import nl.edemtb.mtbclinicsapplication.services.PictureService;
import nl.edemtb.mtbclinicsapplication.services.RoutesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = RouteController.class)
@ContextConfiguration(classes = RouteController.class)
@WithMockUser(username = "admin", roles = "ADMIN")
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoutesService routesService;

    @MockBean
    private PictureService pictureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetAllRoutes() throws Exception {
    // Arrange
        when(routesService.getAllRoutes()).thenReturn(List.of(new RouteDto()));

        mockMvc.perform(MockMvcRequestBuilders
                        .request(HttpMethod.GET,"/routes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetRouteById() throws Exception {
        // Arrange
        Long routeId = 1L;
        RouteDto route = new RouteDto();
        route.setId(routeId);
        route.setName("Route 1");
        route.setRouteInformation("Description 1");

        when(routesService.getRouteById(routeId)).thenReturn(route);

        // Act & Assert
        mockMvc.perform(get("/routes/{id}", routeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(routeId))
                .andExpect(jsonPath("$.name").value("Route 1"))
                .andExpect(jsonPath("$.routeInformation").value("Description 1"));
    }
}
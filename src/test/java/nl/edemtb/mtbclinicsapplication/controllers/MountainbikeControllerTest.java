package nl.edemtb.mtbclinicsapplication.controllers;

import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.services.MountainbikeService;
import nl.edemtb.mtbclinicsapplication.services.PictureService;
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

@WebMvcTest(value = MountainbikeController.class)
@ContextConfiguration(classes = MountainbikeController.class)
@WithMockUser(username = "admin", roles = "ADMIN")
class MountainbikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MountainbikeService mountainbikeService;

    @MockBean
    private PictureService pictureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMountainbikes() throws Exception {
        // Arrange
        when(mountainbikeService.getAllMountainbikes()).thenReturn(List.of(new MountainbikeDto()));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .request(HttpMethod.GET, "/mountainbikes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void searchBySizeAndForAdult() throws Exception {
        // Arrange
        String size = "medium";
        Boolean forAdult = true;
        Boolean fullSuspension = true;
        List<MountainbikeDto> mtbs = List.of(new MountainbikeDto());

        when(mountainbikeService.searchBySizeAndForAdult(size, forAdult, fullSuspension)).thenReturn(mtbs);

        // Act & Assert
        mockMvc.perform(get("/mountainbikes/search")
                        .param("size", size)
                        .param("forAdult", String.valueOf(forAdult))
                        .param("fullSuspension", String.valueOf(fullSuspension))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists());
    }


}
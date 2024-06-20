package nl.edemtb.mtbclinicsapplication.controllers;


import nl.edemtb.mtbclinicsapplication.services.MountainbikeService;
import nl.edemtb.mtbclinicsapplication.services.PictureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test

    void getAllMountainbikes() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .request(HttpMethod.GET, "/mountainbikes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.mappers.MountainbikeMapper;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.repositories.MountainbikeRepository;
import nl.edemtb.mtbclinicsapplication.services.MountainbikeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MountainbikeServiceIntegrationTest {

    @Autowired
    private MountainbikeService mountainbikeService;

    @MockBean
    private MountainbikeRepository mountainbikeRepository;

    @Autowired
    private MountainbikeMapper mountainbikeMapper;

    @Test
    public void testGetAllMountainbikes() {
        // Arrange
        Mountainbike mountainbike1 = new Mountainbike();
        mountainbike1.setId(1L);
        mountainbike1.setName("Mountainbike1");
        mountainbike1.setWheelSize("26 inch");
        mountainbike1.setFrameSize("medium");
        mountainbike1.setGears(10);
        mountainbike1.setPricePerDayPart(50);
        mountainbike1.setAmount(10);
        mountainbike1.setForAdult(true);
        mountainbike1.setFullSuspension(false);
        mountainbike1.setAvailable(true);

        Mountainbike mountainbike2 = new Mountainbike();
        mountainbike2.setId(2L);
        mountainbike2.setName("Mountainbike2");
        mountainbike2.setWheelSize("29 inch");
        mountainbike2.setFrameSize("large");
        mountainbike2.setGears(24);
        mountainbike2.setPricePerDayPart(60);
        mountainbike2.setAmount(5);
        mountainbike2.setForAdult(true);
        mountainbike2.setFullSuspension(true);
        mountainbike2.setAvailable(true);

        List<Mountainbike> mountainbikeList = Arrays.asList(mountainbike1, mountainbike2);

        when(mountainbikeRepository.findAll()).thenReturn(mountainbikeList);

        // Act
        List<MountainbikeDto> result = mountainbikeService.getAllMountainbikes();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Mountainbike1");
        assertThat(result.get(1).getName()).isEqualTo("Mountainbike2");
    }
}
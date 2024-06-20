package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.MountainbikeMapper;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.repositories.MountainbikeRepository;
import nl.edemtb.mtbclinicsapplication.services.MountainbikeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MountainbikeServiceIntegrationTest {

    private Mountainbike mountainbike1;
    private Mountainbike mountainbike2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mountainbike1 = new Mountainbike();
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

        mountainbike2 = new Mountainbike();
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

        when(mountainbikeRepository.findById(1L)).thenReturn(Optional.of(mountainbike1));
        when(mountainbikeRepository.findById(2L)).thenReturn(Optional.of(mountainbike2));
    }

    @Autowired
    private MountainbikeService mountainbikeService;

    @MockBean
    private MountainbikeRepository mountainbikeRepository;

    @Autowired
    private MountainbikeMapper mountainbikeMapper;

    @Test
    public void testGetAllMountainbikes() {
        // Arrange
        List<Mountainbike> mountainbikeList = Arrays.asList(mountainbike1, mountainbike2);

        when(mountainbikeRepository.findAll()).thenReturn(mountainbikeList);

        // Act
        List<MountainbikeDto> result = mountainbikeService.getAllMountainbikes();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Mountainbike1");
        assertThat(result.get(1).getName()).isEqualTo("Mountainbike2");
    }

    @Test
    public void testGetMountainbikeById() {
        // Act
        MountainbikeDto result = mountainbikeService.getMountainbikeById(1L);

        // Assert
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Mountainbike1");
        assertThat(result.getWheelSize()).isEqualTo("26 inch");
    }

    @Test
    public void testGetMountainbikeById_NotFound() {
        // Arrange
        when(mountainbikeRepository.findById(3L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> mountainbikeService.getMountainbikeById(3L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("No mountainbike found with id 3");
    }

    @Test
    public void testSearchBySizeAndForAdult() {
        // Arrange
        when(mountainbikeRepository.searchBySizeAndForAdult("large", true, true))
                .thenReturn(List.of(mountainbike2));

        // Act
        List<MountainbikeDto> result = mountainbikeService.searchBySizeAndForAdult("large", true, true);

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getName()).isEqualTo("Mountainbike2");
        assertThat(result.getFirst().getWheelSize()).isEqualTo("29 inch");
    }

    @Test
    public void testSearchBySizeAndForAdult_NoResults() {
        // Arrange
        when(mountainbikeRepository.searchBySizeAndForAdult("S", false, false))
                .thenReturn(List.of());

        // Act
        List<MountainbikeDto> result = mountainbikeService.searchBySizeAndForAdult("S", false, false);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}
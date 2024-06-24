package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeInputDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.MountainbikeMapper;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.models.Picture;
import nl.edemtb.mtbclinicsapplication.repositories.MountainbikeRepository;
import nl.edemtb.mtbclinicsapplication.repositories.PictureUploadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MountainbikeServiceIntegrationTest {

    private Mountainbike mountainbike1;
    private Mountainbike mountainbike2;
    private Picture picture1;

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

        picture1 = new Picture();
        picture1.setFileName("picture1.jpg");
        mountainbike1.setPicture(picture1);

        when(mountainbikeRepository.findById(1L)).thenReturn(Optional.of(mountainbike1));
        when(mountainbikeRepository.findById(2L)).thenReturn(Optional.of(mountainbike2));
    }

    @Autowired
    private MountainbikeService mountainbikeService;

    @MockBean
    private MountainbikeRepository mountainbikeRepository;

    @MockBean
    private PictureService pictureService;

    @MockBean
    private PictureUploadRepository uploadRepository;


    @Test
    public void testGetAllMountainbikes() {
        // Arrange
        List<Mountainbike> mountainbikes = Arrays.asList(mountainbike1, mountainbike2);
        when(mountainbikeRepository.findAll()).thenReturn(mountainbikes);

        // Act
        List<MountainbikeDto> result = mountainbikeService.getAllMountainbikes();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
    }

    @Test
    public void testGetMountainbikeById() {
        // Arrange
        MountainbikeDto dto = new MountainbikeDto();
        dto.setId(1L);

        // Act
        MountainbikeDto result = mountainbikeService.getMountainbikeById(1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
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

    @Test
    public void testAddMountainbike() {
        // Arrange
        MountainbikeInputDto inputDto = new MountainbikeInputDto();
        inputDto.setName("Mountainbike3");
        inputDto.setWheelSize("28 inch");
        inputDto.setFrameSize("small");
        inputDto.setGears(12);
        inputDto.setPricePerDayPart(40);
        inputDto.setAmount(8);
        inputDto.setForAdult(false);
        inputDto.setFullSuspension(false);
        inputDto.setAvailable(true);

        Mountainbike mountainbike3 = MountainbikeMapper.transferToMountainbike(inputDto);
        mountainbike3.setId(3L);

        when(mountainbikeRepository.save(any(Mountainbike.class))).thenAnswer(invocation -> {
            Mountainbike savedMountainbike = invocation.getArgument(0);
            savedMountainbike.setId(3L);
            return savedMountainbike;
        });

        // Act
        MountainbikeDto result = mountainbikeService.addMountainbike(inputDto);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getName()).isEqualTo("Mountainbike3");
    }

    @Test
    public void testDeleteMountainbike() {
        // Act
        mountainbikeService.deleteMountainbike(1L);

        // Assert
        verify(mountainbikeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteMountainbike_NotFound() {
        // Arrange
        when(mountainbikeRepository.findById(3L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> mountainbikeService.deleteMountainbike(3L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("No mountainbike found with id 3");
    }

    @Test
    public void testUpdateMountainbike() {
        // Arrange
        MountainbikeInputDto inputDto = new MountainbikeInputDto();
        inputDto.setAmount(15);
        inputDto.setAvailable(true);
        inputDto.setPricePerDayPart(55);

        when(mountainbikeRepository.findById(1L)).thenReturn(Optional.of(mountainbike1));
        when(mountainbikeRepository.save(any(Mountainbike.class))).thenReturn(mountainbike1);

        // Act
        MountainbikeDto result = mountainbikeService.updateMountainbike(1L, inputDto);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getAmount()).isEqualTo(15);
        assertThat(result.getPricePerDayPart()).isEqualTo(55);
    }

    @Test
    public void testUpdateMountainbike_NotFound() {
        // Arrange
        MountainbikeInputDto inputDto = new MountainbikeInputDto();
        inputDto.setAmount(15);
        inputDto.setAvailable(true);
        inputDto.setPricePerDayPart(55);

        when(mountainbikeRepository.findById(3L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> mountainbikeService.updateMountainbike(3L, inputDto))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("No mountainbike found with id 3");
    }

    @Test
    public void testGetPictureFromMountainbike() {
        // Arrange
        Resource resource = mock(Resource.class);
        when(pictureService.downLoadPicture("picture1.jpg")).thenReturn(resource);

        // Act
        Resource result = mountainbikeService.getPictureFromMountainbike(1L);

        // Assert
        assertThat(result).isNotNull();
    }

    @Test
    public void testGetPictureFromMountainbike_NotFound() {
        // Arrange
        when(mountainbikeRepository.findById(3L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> mountainbikeService.getPictureFromMountainbike(3L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Mountainbike 3 not found.");
    }

    @Test
    public void testGetPictureFromMountainbike_NoPicture() {
        // Arrange
        mountainbike1.setPicture(null);
        when(mountainbikeRepository.findById(1L)).thenReturn(Optional.of(mountainbike1));

        // Act & Assert
        assertThatThrownBy(() -> mountainbikeService.getPictureFromMountainbike(1L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Mountainbike1 had no photo.");
    }

    @Test
    public void testAssignPictureToMountainbike() {
        // Arrange
        when(uploadRepository.findByFileName("picture1.jpg")).thenReturn(Optional.of(picture1));
        when(mountainbikeRepository.save(any(Mountainbike.class))).thenReturn(mountainbike1);

        // Act
        Mountainbike result = mountainbikeService.assignPictureToMountainbike("picture1.jpg", 1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getPicture()).isEqualTo(picture1);
    }

    @Test
    public void testAssignPictureToMountainbike_NotFound() {
        // Arrange
        when(mountainbikeRepository.findById(3L)).thenReturn(Optional.empty());
        when(uploadRepository.findByFileName("picture1.jpg")).thenReturn(Optional.of(picture1));

        // Act & Assert
        assertThatThrownBy(() -> mountainbikeService.assignPictureToMountainbike("picture1.jpg", 3L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Mountainbike or picture not found.");
    }

    @Test
    public void testAssignPictureToMountainbike_PictureNotFound() {
        // Arrange
        when(uploadRepository.findByFileName("picture1.jpg")).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> mountainbikeService.assignPictureToMountainbike("picture1.jpg", 1L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Mountainbike or picture not found.");
    }
}
package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingDto;
import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingInputDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.models.Picture;
import nl.edemtb.mtbclinicsapplication.models.Training;
import nl.edemtb.mtbclinicsapplication.repositories.PictureUploadRepository;
import nl.edemtb.mtbclinicsapplication.repositories.TrainingRepository;
import nl.edemtb.mtbclinicsapplication.services.PictureService;
import nl.edemtb.mtbclinicsapplication.services.TrainingService;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingServiceUnitTest {

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private PictureService pictureService;

    @Mock
    private PictureUploadRepository uploadRepository;

    @InjectMocks
    private TrainingService trainingService;

    private Training training1;
    private Training training2;
    private Picture picture1;

    @BeforeEach
    void setUp() {
        training1 = new Training();
        training1.setId(1L);
        training1.setName("Training1");
        training1.setLocation("Location1");
        training1.setPrice(100.0);
        training1.setTrainingInGroup(true);

        training2 = new Training();
        training2.setId(2L);
        training2.setName("Training2");
        training2.setLocation("Location2");
        training2.setPrice(200.0);
        training2.setTrainingInGroup(false);

        picture1 = new Picture();
        picture1.setFileName("picture1.jpg");

        training1.setPicture(picture1);

        when(trainingRepository.findById(1L)).thenReturn(Optional.of(training1));
        when(trainingRepository.findById(2L)).thenReturn(Optional.of(training2));
    }

    @Test
    public void testGetAllTrainings() {
        // Arrange
        List<Training> trainings = Arrays.asList(training1, training2);
        when(trainingRepository.findAll()).thenReturn(trainings);

        // Act
        List<TrainingDto> result = trainingService.getAllTrainings();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        // Additional asserts to verify the conversion logic
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(1).getId()).isEqualTo(2L);
    }

    @Test
    public void testGetTrainingById() {
        // Arrange
        TrainingDto expectedDto = new TrainingDto();
        expectedDto.setId(1L);
        expectedDto.setName("Training1");
        expectedDto.setLocation("Location1");
        expectedDto.setPrice(100.0);
        expectedDto.setTrainingInGroup(true);

        // Act
        TrainingDto result = trainingService.getTrainingById(1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Training1");
        assertThat(result.getLocation()).isEqualTo("Location1");
        assertThat(result.getPrice()).isEqualTo(100.0);
        assertThat(result.getTrainingInGroup()).isTrue();
    }

    @Test
    public void testGetTrainingById_NotFound() {
        // Arrange
        when(trainingRepository.findById(3L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> trainingService.getTrainingById(3L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Training met id: 3 niet gevonden!");
    }

    @Test
    public void testAddTraining() {
        // Arrange
        TrainingInputDto inputDto = new TrainingInputDto();
        inputDto.setName("Training3");
        inputDto.setLocation("Location3");
        inputDto.setPrice(150.0);
        inputDto.setTrainingInGroup(true);

        Training training3 = new Training();
        training3.setId(3L);
        training3.setName("Training3");
        training3.setLocation("Location3");
        training3.setPrice(150.0);
        training3.setTrainingInGroup(true);

        when(trainingRepository.save(any(Training.class))).thenReturn(training3);

        // Act
        TrainingDto result = trainingService.addTraining(inputDto);

        // Assert
        assertThat(result).isNotNull();
        verify(trainingRepository, times(1)).save(any(Training.class));
        assertThat(result.getName()).isEqualTo("Training3");
        assertThat(result.getLocation()).isEqualTo("Location3");
        assertThat(result.getPrice()).isEqualTo(150.0);
        assertThat(result.getTrainingInGroup()).isTrue();
    }

    @Test
    public void testDeleteTraining() {
        // Act
        trainingService.deleteTraining(1L);

        // Assert
        verify(trainingRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateTraining() {
        // Arrange
        TrainingInputDto inputDto = new TrainingInputDto();
        inputDto.setLocation("New Location");
        inputDto.setPrice(300.0);
        inputDto.setTrainingInGroup(true);

        Training updatedTraining = new Training();
        updatedTraining.setId(1L);
        updatedTraining.setLocation("New Location");
        updatedTraining.setPrice(300.0);
        updatedTraining.setTrainingInGroup(true);

        when(trainingRepository.findById(1L)).thenReturn(Optional.of(training1));
        when(trainingRepository.save(any(Training.class))).thenReturn(updatedTraining);

        // Act
        TrainingDto result = trainingService.updateTraining(1L, inputDto);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getLocation()).isEqualTo("New Location");
        assertThat(result.getPrice()).isEqualTo(300.0);
        assertThat(result.getTrainingInGroup()).isTrue();
    }

    @Test
    public void testUpdateTraining_NotFound() {
        // Arrange
        TrainingInputDto inputDto = new TrainingInputDto();
        inputDto.setLocation("New Location");
        inputDto.setPrice(300.0);
        inputDto.setTrainingInGroup(true);

        when(trainingRepository.findById(3L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> trainingService.updateTraining(3L, inputDto))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Training met id:3 niet gevonden");
    }

    @Test
    public void testGetPictureFromTraining() throws MalformedURLException, FileNotFoundException {
        // Arrange
        Resource resource = mock(Resource.class);
        when(pictureService.downLoadPicture("picture1.jpg")).thenReturn(resource);

        // Act
        Resource result = trainingService.getPictureFromTraining(1L);

        // Assert
        assertThat(result).isNotNull();
        verify(pictureService, times(1)).downLoadPicture("picture1.jpg");
    }

    @Test
    public void testGetPictureFromTraining_NotFound() {
        // Arrange
        when(trainingRepository.findById(3L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> trainingService.getPictureFromTraining(3L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Training with id: 3 not found.");
    }

    @Test
    public void testGetPictureFromTraining_NoPicture() {
        // Arrange
        training1.setPicture(null);
        when(trainingRepository.findById(1L)).thenReturn(Optional.of(training1));

        // Act & Assert
        assertThatThrownBy(() -> trainingService.getPictureFromTraining(1L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Training with id: 1 had no photo.");
    }

    @Test
    public void testAssignPhotoToTraining() {
        // Arrange
        when(uploadRepository.findByFileName("picture1.jpg")).thenReturn(Optional.of(picture1));
        when(trainingRepository.save(any(Training.class))).thenReturn(training1);

        // Act
        Training result = trainingService.assignPhotoToTraining("picture1.jpg", 1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getPicture()).isEqualTo(picture1);
    }

    @Test
    public void testAssignPhotoToTraining_NotFound() {
        // Arrange
        when(trainingRepository.findById(3L)).thenReturn(Optional.empty());
        when(uploadRepository.findByFileName("picture1.jpg")).thenReturn(Optional.of(picture1));

        // Act & Assert
        assertThatThrownBy(() -> trainingService.assignPhotoToTraining("picture1.jpg", 3L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Training or picture not found.");
    }

    @Test
    public void testAssignPhotoToTraining_PictureNotFound() {
        // Arrange
        when(uploadRepository.findByFileName("picture1.jpg")).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> trainingService.assignPhotoToTraining("picture1.jpg", 1L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Training or picture not found.");
    }
}
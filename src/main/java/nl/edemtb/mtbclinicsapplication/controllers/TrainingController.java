package nl.edemtb.mtbclinicsapplication.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingDto;
import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingInputDto;
import nl.edemtb.mtbclinicsapplication.models.Training;
import nl.edemtb.mtbclinicsapplication.services.PictureService;
import nl.edemtb.mtbclinicsapplication.services.TrainingService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RequestMapping("/trainings")
@RestController
public class TrainingController {

    private final TrainingService trainingService;
    private final PictureService pictureService;

    public TrainingController(TrainingService trainingService, PictureService pictureService) {
        this.trainingService = trainingService;
        this.pictureService = pictureService;
    }

    @GetMapping()
    public ResponseEntity<List<TrainingDto>> getAllTrainings() {

        List<TrainingDto> trainings = trainingService.getAllTrainings();
        return ResponseEntity.ok(trainings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingDto> getTrainingById(@PathVariable Long id) {

        TrainingDto training = trainingService.getTrainingById(id);
        return ResponseEntity.ok(training);
    }

    @PostMapping()
    public ResponseEntity<Object> addTraining(@Valid @RequestBody TrainingInputDto inputDto) {

        TrainingDto dto = trainingService.addTraining(inputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingDto> updateTraining(@PathVariable Long id, @RequestBody TrainingInputDto updateTraining) {
        TrainingDto dto = trainingService.updateTraining(id, updateTraining);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/{id}/picture")
    public ResponseEntity<Training> addPictureToTraining(@PathVariable("id") Long id,
                                                         @RequestBody MultipartFile file)
            throws IOException {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/trainings/")
                .path(Objects.requireNonNull(id.toString()))
                .path("/picture")
                .toUriString();
        String fileName = pictureService.storePicture(file);
        Training training = trainingService.assignPhotoToTraining(fileName, id);

        return ResponseEntity.created(URI.create(url)).body(training);

    }

    @GetMapping("/{id}/picture")
    public ResponseEntity<Resource> getTrainingPicture(@PathVariable("id") Long id, HttpServletRequest request) throws FileNotFoundException {
        Resource resource = trainingService.getPictureFromTraining(id);

        String image;

        try {
            image = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {

            image = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(image))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

}

package nl.edemtb.mtbclinicsapplication.controllers;

import jakarta.validation.Valid;
import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingDto;
import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingInputDto;
import nl.edemtb.mtbclinicsapplication.services.TrainingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/trainings")
@RestController
public class TrainingController {

        private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
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
}

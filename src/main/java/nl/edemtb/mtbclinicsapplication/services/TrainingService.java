package nl.edemtb.mtbclinicsapplication.services;


import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingDto;
import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingInputDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.TrainingMapper;
import nl.edemtb.mtbclinicsapplication.models.Training;
import nl.edemtb.mtbclinicsapplication.repositories.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {


    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;

    public TrainingService(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    public List<TrainingDto> getAllTrainings() {
        List<Training> trainings = trainingRepository.findAll();
        return trainingMapper.transferTrainingListToDto(trainings);
    }

    public TrainingDto getTrainingById(Long id) {
        Optional<Training> trainingOptional = trainingRepository.findById(id);

        if (trainingOptional.isPresent()) {
            Training training = trainingOptional.get();
            return trainingMapper.transferToDto(training);
        } else {
            throw new RecordNotFoundException("Training met id: " + id + " niet gevonden!");
        }
    }

    public TrainingDto addTraining(TrainingInputDto dto) {

        Training training = trainingMapper.transferToTraining(dto);
        trainingRepository.save(training);

        return trainingMapper.transferToDto(training);
    }

    public void deleteTraining(Long id) {
        if (trainingRepository.findById(id).isPresent())
            trainingRepository.deleteById(id);
    }

    public TrainingDto updateTraining(Long id, TrainingInputDto inputDto) {

        if (trainingRepository.findById(id).isPresent()) {
            return trainingMapper.trainingInputMapper(id, inputDto);
        } else {
            throw new RecordNotFoundException("Training met id:" + id + " niet gevonden");
        }
    }
}

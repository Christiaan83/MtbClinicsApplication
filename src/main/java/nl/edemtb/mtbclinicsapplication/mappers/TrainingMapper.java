package nl.edemtb.mtbclinicsapplication.mappers;

import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingDto;
import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingInputDto;
import nl.edemtb.mtbclinicsapplication.models.Training;
import nl.edemtb.mtbclinicsapplication.repositories.TrainingRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainingMapper {

    private final TrainingRepository trainingRepository;

    public TrainingMapper(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public Training transferToTraining(TrainingInputDto dto) {
        if (dto == null) return null;

        Training training = new Training();
        training.setName(dto.getName());
        training.setDifficulty(dto.getDifficulty());
        training.setLocation(dto.getLocation());
        training.setDescription(dto.getDescription());
        training.setPrice(dto.getPrice());
        training.setStartTime(dto.getStartTime());
        training.setEndTime(dto.getEndTime());
        training.setDateList(dto.getDateList());
        training.setTrainingInGroup(dto.getTrainingInGroup());
        return training;
    }

    public TrainingDto transferToDto(Training training) {
        if (training == null) return null;

        TrainingDto dto = new TrainingDto();
        dto.setId(training.getId());
        dto.setName(training.getName());
        dto.setDifficulty(training.getDifficulty());
        dto.setLocation(training.getLocation());
        dto.setDescription(training.getDescription());
        dto.setPrice(training.getPrice());
        dto.setStartTime(training.getStartTime());
        dto.setEndTime(training.getEndTime());
        dto.setDateList(training.getDateList());
        dto.setTrainingInGroup(training.getTrainingInGroup());
        return dto;
    }

    public List<TrainingDto> transferTrainingListToDto(List<Training> trainings) {
        List<TrainingDto> trainingDtoList = new ArrayList<>();

        for (Training training : trainings) {
            trainingDtoList.add(transferToDto(training));
        }
        return trainingDtoList;
    }

    public TrainingDto trainingInputMapper(Long id, TrainingInputDto inputDto) {

        trainingRepository.findById(id);
        Training training = trainingRepository.findById(id).get();
        training.setLocation(inputDto.getLocation());
        training.setPrice(inputDto.getPrice());
        training.setStartTime(inputDto.getStartTime());
        training.setEndTime(inputDto.getEndTime());
        training.setDateList(inputDto.getDateList());
        training.setTrainingInGroup(inputDto.getTrainingInGroup());
        trainingRepository.save(training);
        return transferToDto(training);

    }

}

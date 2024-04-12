package nl.edemtb.mtbclinicsapplication.mappers;

import nl.edemtb.mtbclinicsapplication.dtos.TrainingDto;
import nl.edemtb.mtbclinicsapplication.dtos.TrainingInpuDto;
import nl.edemtb.mtbclinicsapplication.models.Training;
import nl.edemtb.mtbclinicsapplication.repositories.TrainingRepository;

import java.util.ArrayList;
import java.util.List;

public class TrainingMapper {

    private final TrainingRepository trainingRepository;

    public TrainingMapper(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public Training transferToTraining(TrainingInpuDto dto){
        if (dto==null) return null;

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

    public TrainingDto transferToDto(Training training){
        if (training == null) return null;

        TrainingDto Dto = new TrainingDto();
        Dto.setName(training.getName());
        Dto.setDifficulty(training.getDifficulty());
        Dto.setLocation(training.getLocation());
        Dto.setDescription(training.getDescription());
        Dto.setPrice(training.getPrice());
        Dto.setStartTime(training.getStartTime());
        Dto.setEndTime(training.getEndTime());
        Dto.setDateList(training.getDateList());
        Dto.setTrainingInGroup(training.getTrainingInGroup());
        return Dto;
    }
    public List<TrainingDto> transferTrainingListToDto(List<Training> trainings){
        List<TrainingDto> trainingDtoList = new ArrayList<>();

        for (Training training : trainings){
            trainingDtoList.add(transferToDto(training));
        }
        return trainingDtoList;
    }
    public TrainingDto trainingInputMapper(Long id, TrainingInpuDto inputDto){

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

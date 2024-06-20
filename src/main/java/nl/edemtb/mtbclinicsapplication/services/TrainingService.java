package nl.edemtb.mtbclinicsapplication.services;


import jakarta.transaction.Transactional;
import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingDto;
import nl.edemtb.mtbclinicsapplication.dtos.training.TrainingInputDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.TrainingMapper;
import nl.edemtb.mtbclinicsapplication.models.Picture;
import nl.edemtb.mtbclinicsapplication.models.Training;
import nl.edemtb.mtbclinicsapplication.repositories.PictureUploadRepository;
import nl.edemtb.mtbclinicsapplication.repositories.TrainingRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {


    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final PictureService pictureService;
    private final PictureUploadRepository uploadRepository;

    public TrainingService(TrainingRepository trainingRepository, TrainingMapper trainingMapper, PictureService pictureService, PictureUploadRepository uploadRepository) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
        this.pictureService = pictureService;
        this.uploadRepository = uploadRepository;
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

    @Transactional
    public Resource getPictureFromTraining(Long id) throws FileNotFoundException {
        Optional<Training> optionalTraining = trainingRepository.findById(id);
        if(optionalTraining.isEmpty()){
            throw new RecordNotFoundException("Training with id: " + id + " not found.");
        }
        Picture picture = optionalTraining.get().getPicture();
        if(picture == null){
            throw new RecordNotFoundException("Training with id: " + id + " had no photo.");
        }
        return pictureService.downLoadPicture(picture.getFileName());
    }
    @Transactional
    public Training assignPhotoToTraining(String filename,Long id){
        Optional<Training> optionalTraining = trainingRepository.findById(id);
        Optional<Picture> optionalPicture = uploadRepository.findByFileName(filename);

        if(optionalTraining.isPresent() && optionalPicture.isPresent()){
            Picture picture = optionalPicture.get();
            Training training = optionalTraining.get();
            training.setPicture(picture);
            return trainingRepository.save(training);
        }else{
            throw new RecordNotFoundException("Training or picture not found.");
        }
    }
}

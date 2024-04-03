package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.repositories.MountainbikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MountainbikeService {

        private final MountainbikeRepository mountainbikeRepository;
        public MountainbikeService(MountainbikeRepository mountainbikeRepository) {
                this.mountainbikeRepository = mountainbikeRepository;}

        public List<Mountainbike> getAllMountainbikes() {
                List<Mountainbike> mtbList = mountainbikeRepository.findAll();

                return mtbList;
        }


public Mountainbike getMountainbikeById(Long id){
        Optional<Mountainbike> mtbOptional = mountainbikeRepository.findById(id);
        if(mtbOptional.isPresent()){
            return mtbOptional.get();
        }else {
                throw new RecordNotFoundException(" geen Mountainbike gevonden");
        }
}
}
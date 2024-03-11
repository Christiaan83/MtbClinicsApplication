package nl.edemtb.mtbclinicsapplication.controllers;

import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.repositories.MountainbikeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/mountainbikes")
@RestController
public class MountainbikeController {

    private final MountainbikeRepository mountainbikeRepository;

    public MountainbikeController(MountainbikeRepository mountainbikeRepository) {
        this.mountainbikeRepository = mountainbikeRepository;
    }

    @GetMapping("/")

    public ResponseEntity<String> getAllMountainbikes(){
        return ResponseEntity.ok("");
    }

    @GetMapping("/{id}")

    public ResponseEntity<Mountainbike> getMountainbike(@PathVariable("id") long id) {
        Optional<Mountainbike> mountainbike = mountainbikeRepository.findById(id);

        Mountainbike mountainbike1;
        if (mountainbike.isEmpty()){
            throw new RecordNotFoundException("No mountainbike found with id: " + id);
        } else {
            mountainbike1 = mountainbike.get();
        }

        return ResponseEntity.ok().body(mountainbike1);
    }


}




package nl.edemtb.mtbclinicsapplication.controllers;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.services.MountainbikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/mountainbikes")
@RestController
public class MountainbikeController {


    private final MountainbikeService mountainbikeService;


    public MountainbikeController(MountainbikeService mountainbikeService) {
        this.mountainbikeService = mountainbikeService;
    }


    @GetMapping()

    public ResponseEntity<List<Mountainbike>> getAllMountainbikes(){
        List<Mountainbike> mtbs;
        mtbs = mountainbikeService.getAllMountainbikes();
        return ResponseEntity.ok(mtbs);
    }

    @GetMapping("/{id}")

    public ResponseEntity<Mountainbike> getMountainbike(@PathVariable("id") long id) {

        Mountainbike mtb = mountainbikeService.getMountainbikeById(id);

        return ResponseEntity.ok().body(mtb);
    }

}




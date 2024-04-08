package nl.edemtb.mtbclinicsapplication.controllers;

import jakarta.validation.Valid;
import nl.edemtb.mtbclinicsapplication.dtos.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.dtos.MountainbikeInputDto;
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

    public ResponseEntity<List<MountainbikeDto>> getAllMountainbikes() {
        List<MountainbikeDto> mtbs;
        mtbs = mountainbikeService.getAllMountainbikes();
        return ResponseEntity.ok(mtbs);
    }

    @GetMapping("/{id}")

    public ResponseEntity<MountainbikeDto> getMountainbike(@PathVariable("id") long id) {

        MountainbikeDto mtb = mountainbikeService.getMountainbikeById(id);

        return ResponseEntity.ok().body(mtb);
    }

    @PostMapping()
    public ResponseEntity<Object> addMountainbike(@Valid @RequestBody MountainbikeInputDto mountainbikeInputDto) {
        MountainbikeDto dto = mountainbikeService.addMountainbike(mountainbikeInputDto);

        return ResponseEntity.created(null).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMountainbike(@PathVariable Long id){

        mountainbikeService.deleteMountainbike(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MountainbikeDto> updateMountainbike(@PathVariable Long id, @RequestBody MountainbikeInputDto updatedMountainbike){

        MountainbikeDto dto = mountainbikeService.updateMountainbike(id, updatedMountainbike);
        return ResponseEntity.ok().body(dto);
    }
}




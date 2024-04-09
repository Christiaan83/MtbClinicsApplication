package nl.edemtb.mtbclinicsapplication.controllers;

import jakarta.validation.Valid;
import nl.edemtb.mtbclinicsapplication.dtos.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.dtos.MountainbikeInputDto;
import nl.edemtb.mtbclinicsapplication.services.MountainbikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/mountainbikes")
@RestController
public class MountainbikeController {


    private final MountainbikeService mountainbikeService;
    List<MountainbikeDto> mtbs;


    public MountainbikeController(MountainbikeService mountainbikeService) {
        this.mountainbikeService = mountainbikeService;
    }


    @GetMapping()

    public ResponseEntity<List<MountainbikeDto>> getAllMountainbikes() {

        mtbs = mountainbikeService.getAllMountainbikes();
        return ResponseEntity.ok(mtbs);
    }

    @GetMapping("/{id}")

    public ResponseEntity<MountainbikeDto> getMountainbike(@PathVariable("id") long id) {

        MountainbikeDto mtb = mountainbikeService.getMountainbikeById(id);
        return ResponseEntity.ok().body(mtb);
    }

    @GetMapping("/search/size/{size}")

    public ResponseEntity<List<MountainbikeDto>> searchBySize(@PathVariable("size") String size) {

        mtbs = mountainbikeService.searchBySize(size);
        return ResponseEntity.ok().body(mtbs);
    }

    @GetMapping("/search/for-adult/{boolean}")
    public ResponseEntity<List<MountainbikeDto>> searchByForAdult(@PathVariable("boolean") Boolean forAdult) {

        mtbs = mountainbikeService.searchByForAdult(forAdult);
        return ResponseEntity.ok().body(mtbs);
    }


    @PostMapping()
    public ResponseEntity<Object> addMountainbike(@Valid @RequestBody MountainbikeInputDto mountainbikeInputDto) {

        MountainbikeDto dto = mountainbikeService.addMountainbike(mountainbikeInputDto);
        return ResponseEntity.created(null).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMountainbike(@PathVariable Long id) {

        mountainbikeService.deleteMountainbike(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MountainbikeDto> updateMountainbike(@PathVariable Long id, @RequestBody MountainbikeInputDto updatedMountainbike) {

        MountainbikeDto dto = mountainbikeService.updateMountainbike(id, updatedMountainbike);
        return ResponseEntity.ok().body(dto);
    }
}




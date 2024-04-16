package nl.edemtb.mtbclinicsapplication.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeDto;
import nl.edemtb.mtbclinicsapplication.dtos.mountainbike.MountainbikeInputDto;
import nl.edemtb.mtbclinicsapplication.models.Mountainbike;
import nl.edemtb.mtbclinicsapplication.services.MountainbikeService;
import nl.edemtb.mtbclinicsapplication.services.PictureService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RequestMapping("/mountainbikes")
@RestController
public class MountainbikeController {


    private final MountainbikeService mountainbikeService;
    private final PictureService pictureService;



    public MountainbikeController(MountainbikeService mountainbikeService, PictureService pictureService) {
        this.mountainbikeService = mountainbikeService;
        this.pictureService = pictureService;
    }


    @GetMapping()

    public ResponseEntity<List<MountainbikeDto>> getAllMountainbikes() {

        var mtbs = mountainbikeService.getAllMountainbikes();
        return ResponseEntity.ok(mtbs);
    }

    @GetMapping("/{id}")

    public ResponseEntity<MountainbikeDto> getMountainbike(@PathVariable("id") long id) {

        MountainbikeDto mtb = mountainbikeService.getMountainbikeById(id);
        return ResponseEntity.ok().body(mtb);
    }

    @GetMapping("/search/size/{size}")

    public ResponseEntity<List<MountainbikeDto>> searchBySize(@PathVariable("size") String size) {

       var mtbs = mountainbikeService.searchBySize(size);
        return ResponseEntity.ok().body(mtbs);
    }

    @GetMapping("/search/for-adult/{boolean}")
    public ResponseEntity<List<MountainbikeDto>> searchByForAdult(@PathVariable("boolean") Boolean forAdult) {

        var mtbs = mountainbikeService.searchByForAdult(forAdult);
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

    @PostMapping("/{id}/picture")
    public ResponseEntity<Mountainbike> addPhotoToMtb(@PathVariable("id") Long id,
                                                      @RequestParam("file") MultipartFile file)
            throws IOException {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/mountainbikes/")
                .path(Objects.requireNonNull(id.toString()))
                .path("/picture")
                .toUriString();

        String fileName = pictureService.storePicture(file);
        Mountainbike mtb = mountainbikeService.assignPictureToMountainbike(fileName, id);

        return ResponseEntity.created(URI.create(url)).body(mtb);
    }

    @GetMapping("/{id}/picture")
    public ResponseEntity<Resource> getStudentPhoto(@PathVariable("id") Long id, HttpServletRequest request) throws FileNotFoundException {

        Resource resource = mountainbikeService.getPictureFromMountainbike(id);

        String image;

        try{
            image = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {

            image = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(image))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

}




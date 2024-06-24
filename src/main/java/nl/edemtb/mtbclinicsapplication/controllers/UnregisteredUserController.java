package nl.edemtb.mtbclinicsapplication.controllers;

import nl.edemtb.mtbclinicsapplication.dtos.UnregisteredUserDto;
import nl.edemtb.mtbclinicsapplication.services.UnregisteredUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/unregistered-users")
@RestController
public class UnregisteredUserController {

    private final UnregisteredUserService unregisteredUserService;

    public UnregisteredUserController(UnregisteredUserService unregisteredUserService) {
        this.unregisteredUserService = unregisteredUserService;
    }

    @GetMapping()

    public ResponseEntity<List<UnregisteredUserDto>> getAllUnregisteredUsers() {

        List<UnregisteredUserDto> dtoList = unregisteredUserService.findAll();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")

    public ResponseEntity<UnregisteredUserDto> getUnregisteredUserById(@PathVariable("id") Long id) {

        UnregisteredUserDto dto = unregisteredUserService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping()

    public ResponseEntity<UnregisteredUserDto> createUnregisteredUser(@RequestBody UnregisteredUserDto dto) {

        UnregisteredUserDto unregisteredUser = unregisteredUserService.addUnregisteredUser(dto);
        return ResponseEntity.created(null).body(unregisteredUser);
    }

    @PutMapping("/{id}")

    public ResponseEntity<UnregisteredUserDto> updateUnregisteredUser(@PathVariable("id") Long id, @RequestBody UnregisteredUserDto dto) {

        UnregisteredUserDto unregisteredUser = unregisteredUserService.updateUnregisteredUser(id, dto);
        return ResponseEntity.ok(unregisteredUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUnregisteredUser(@PathVariable("id") Long id) {
        unregisteredUserService.deleteUnregisteredUser(id);
        return ResponseEntity.noContent().build();
    }
}
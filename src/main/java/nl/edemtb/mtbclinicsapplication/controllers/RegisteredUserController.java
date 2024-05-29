package nl.edemtb.mtbclinicsapplication.controllers;


import nl.edemtb.mtbclinicsapplication.dtos.RegisteredUserDto;
import nl.edemtb.mtbclinicsapplication.exceptions.BadRequestException;
import nl.edemtb.mtbclinicsapplication.services.RegisteredUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class RegisteredUserController {

    private final RegisteredUserService registeredUserService;

    public RegisteredUserController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<RegisteredUserDto>> getUsers() {

        List<RegisteredUserDto> registeredUserDtos = registeredUserService.getRegisteredUsers();

        return ResponseEntity.ok().body(registeredUserDtos);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<RegisteredUserDto> getUser(@PathVariable("username") String username) {

        RegisteredUserDto optionalUser = registeredUserService.getUser(username);


        return ResponseEntity.ok().body(optionalUser);

    }

    @PostMapping(value = "")
    public ResponseEntity<RegisteredUserDto> createUser(@RequestBody RegisteredUserDto dto) {;

        String newUsername = registeredUserService.createRegisteredUser(dto);
        registeredUserService.addAuthority(newUsername, "ROLE_USER");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<RegisteredUserDto> updateUser(@PathVariable("username") String username, @RequestBody RegisteredUserDto dto) {

        registeredUserService.updateUser(username, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        registeredUserService.deleteRegisteredUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(registeredUserService.getAuthorities(username));
    }

    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            registeredUserService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        registeredUserService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }

}
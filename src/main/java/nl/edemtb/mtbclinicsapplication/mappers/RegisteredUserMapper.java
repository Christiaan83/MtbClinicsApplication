package nl.edemtb.mtbclinicsapplication.mappers;

import nl.edemtb.mtbclinicsapplication.config.PasswordConfiguration;
import nl.edemtb.mtbclinicsapplication.dtos.RegisteredUserDto;
import nl.edemtb.mtbclinicsapplication.models.RegisteredUser;
import nl.edemtb.mtbclinicsapplication.repositories.RegisteredUserRepository;
import org.springframework.stereotype.Component;

@Component
public class RegisteredUserMapper {

    private final PasswordConfiguration passwordConfiguration;
    private final RegisteredUserRepository registeredUserRepository;


    public RegisteredUserMapper(PasswordConfiguration passwordConfiguration, RegisteredUserRepository registeredUserRepository) {
        this.passwordConfiguration = passwordConfiguration;
        this.registeredUserRepository = registeredUserRepository;
    }


    public static RegisteredUserDto fromRegisteredUser(RegisteredUser registereduser){

    var registeredUserDto = new RegisteredUserDto();

    registeredUserDto.setUsername(registereduser.getUsername());
    registeredUserDto.setPassword(registereduser.getPassword());
    registeredUserDto.setFirstName(registereduser.getFirstName());
    registeredUserDto.setLastName(registereduser.getLastName());
    registeredUserDto.setActive(registereduser.getActive());
    registeredUserDto.setApikey(registereduser.getApikey());
    registeredUserDto.setEmail(registereduser.getEmail());
    registeredUserDto.setMobileNumber(registereduser.getMobileNumber());
    registeredUserDto.setAuthorities(registereduser.getAuthorities());

    return registeredUserDto;
}

public RegisteredUser toRegisteredUser(RegisteredUserDto userDto) {

    var registeredUser = new RegisteredUser();

    registeredUser.setUsername(userDto.getUsername());
    registeredUser.setPassword(passwordConfiguration.passwordEncoder().encode(userDto.getPassword()));
    registeredUser.setFirstName(userDto.getFirstName());
    registeredUser.setLastName(userDto.getLastName());
    registeredUser.setActive(userDto.getActive());
    registeredUser.setApikey(userDto.getApikey());
    registeredUser.setEmail(userDto.getEmail());
    registeredUser.setMobileNumber(userDto.getMobileNumber());
    registeredUser.setApikey(userDto.getApikey());
    registeredUser.setAuthorities(userDto.getAuthorities());

    return registeredUser;
}


public void userInputMapper(String username, RegisteredUserDto newUser) {

    RegisteredUser user = registeredUserRepository.findById(username).get();
    if (newUser.getPassword() != null) {
        user.setPassword(passwordConfiguration.passwordEncoder().encode(newUser.getPassword()));
    }
    if (newUser.getFirstName() != null) {
        user.setFirstName(newUser.getFirstName());
    }
    if (newUser.getLastName() != null) {
        user.setLastName(newUser.getLastName());
    }
    if (newUser.getEmail() != null) {
        user.setEmail(newUser.getEmail());
    }
    if (newUser.getUsername() != null) {
        user.setUsername(newUser.getUsername());
    }
    if (newUser.getMobileNumber() != null) {
        user.setMobileNumber(newUser.getMobileNumber());
    }
    if (newUser.getActive() != null) {
        user.setActive(newUser.getActive());
    }
    registeredUserRepository.save(user);
    fromRegisteredUser(user);
}
}
package nl.edemtb.mtbclinicsapplication.mappers;

import nl.edemtb.mtbclinicsapplication.config.PasswordConfiguration;
import nl.edemtb.mtbclinicsapplication.dtos.RegisteredUserDto;
import nl.edemtb.mtbclinicsapplication.models.RegisteredUser;
import org.springframework.stereotype.Component;

@Component
public class RegisteredUserMapper {

    private final PasswordConfiguration passwordConfiguration;


    public RegisteredUserMapper(PasswordConfiguration passwordConfiguration) {
        this.passwordConfiguration = passwordConfiguration;
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

    return registeredUser;
}
}
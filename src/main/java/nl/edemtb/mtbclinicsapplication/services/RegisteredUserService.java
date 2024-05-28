package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.RegisteredUserDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.models.Authority;
import nl.edemtb.mtbclinicsapplication.models.RegisteredUser;
import nl.edemtb.mtbclinicsapplication.repositories.RegisteredUserRepository;
import nl.edemtb.mtbclinicsapplication.utils.RandomStringGenerator;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RegisteredUserService {
    private final RegisteredUserRepository registeredUserRepository;

    public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;

    }


    public List<RegisteredUserDto> getRegisteredUsers() {
        List<RegisteredUserDto> collection = new ArrayList<>();
        List<RegisteredUser> list = registeredUserRepository.findAll();
        for (RegisteredUser registeredUser : list) {
            collection.add(fromRegisteredUser(registeredUser));
        }
        return collection;
    }

    public RegisteredUserDto getUser(String username) {
        RegisteredUserDto dto = new RegisteredUserDto();
        Optional<RegisteredUser> registeredUser = registeredUserRepository.findById(username);
        if (registeredUser.isPresent()){
            dto = fromRegisteredUser(registeredUser.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean registeredUserExists(String username) {
        return registeredUserRepository.existsById(username);
    }

    public String createRegisteredUser(RegisteredUserDto registeredUserDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        registeredUserDto.setApikey(randomString);
        RegisteredUser newRegisteredUser = registeredUserRepository.save(toRegisteredUser(registeredUserDto));
        return newRegisteredUser.getUsername();
    }

    public void deleteRegisteredUser(String username) {
        registeredUserRepository.deleteById(username);
    }

    public void updateUser(String username, RegisteredUserDto newUser) {
        if (!registeredUserRepository.existsById(username)) throw new RecordNotFoundException();
        RegisteredUser user = registeredUserRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        registeredUserRepository.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!registeredUserRepository.existsById(username)) throw new UsernameNotFoundException(username);
        RegisteredUser registeredUser = registeredUserRepository.findById(username).get();
        RegisteredUserDto registereduserDto = fromRegisteredUser(registeredUser);
        return registereduserDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!registeredUserRepository.existsById(username)) throw new UsernameNotFoundException(username);
        RegisteredUser registeredUser = registeredUserRepository.findById(username).get();
        registeredUser.addAuthority(new Authority(username, authority));
        registeredUserRepository.save(registeredUser);
    }

    public void removeAuthority(String username, String authority) {
        if (!registeredUserRepository.existsById(username)) throw new UsernameNotFoundException(username);
        RegisteredUser registeredUser = registeredUserRepository.findById(username).get();
        Authority authorityToRemove = registeredUser.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        registeredUser.removeAuthority(authorityToRemove);
        registeredUserRepository.save(registeredUser);
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
        registeredUser.setPassword(userDto.getPassword());
        registeredUser.setFirstName(userDto.getFirstName());
        registeredUser.setLastName(userDto.getLastName());
        registeredUser.setActive(userDto.getActive());
        registeredUser.setApikey(userDto.getApikey());
        registeredUser.setEmail(userDto.getEmail());
        registeredUser.setMobileNumber(userDto.getMobileNumber());
        registeredUser.setAuthorities(userDto.getAuthorities());
        registeredUser.setApikey(userDto.getApikey());

        return registeredUser;
    }

}

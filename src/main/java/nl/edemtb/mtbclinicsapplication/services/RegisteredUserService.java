package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.RegisteredUserDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.RegisteredUserMapper;
import nl.edemtb.mtbclinicsapplication.models.Authority;
import nl.edemtb.mtbclinicsapplication.models.RegisteredUser;
import nl.edemtb.mtbclinicsapplication.repositories.RegisteredUserRepository;
import nl.edemtb.mtbclinicsapplication.utils.RandomStringGenerator;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RegisteredUserService {
    private final RegisteredUserRepository registeredUserRepository;
    private final RegisteredUserMapper registeredUserMapper;


    public RegisteredUserService(RegisteredUserRepository registeredUserRepository, PasswordEncoder passwordEncoder, RegisteredUserMapper registeredUserMapper) {
        this.registeredUserRepository = registeredUserRepository;

        this.registeredUserMapper = registeredUserMapper;
    }


    public List<RegisteredUserDto> getRegisteredUsers() {
        List<RegisteredUserDto> collection = new ArrayList<>();
        List<RegisteredUser> list = registeredUserRepository.findAll();
        for (RegisteredUser registeredUser : list) {
            collection.add(registeredUserMapper.fromRegisteredUser(registeredUser));
        }
        return collection;
    }

    public RegisteredUserDto getUser(String username) {
        RegisteredUserDto dto = new RegisteredUserDto();
        Optional<RegisteredUser> registeredUser = registeredUserRepository.findById(username);
        if (registeredUser.isPresent()){
            dto = registeredUserMapper.fromRegisteredUser(registeredUser.get());
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
        RegisteredUser newRegisteredUser = registeredUserRepository.save(registeredUserMapper.toRegisteredUser(registeredUserDto));
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
        RegisteredUserDto registereduserDto = registeredUserMapper.fromRegisteredUser(registeredUser);
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


}

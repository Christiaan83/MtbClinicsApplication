package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.dtos.UnregisteredUserDto;
import nl.edemtb.mtbclinicsapplication.exceptions.RecordNotFoundException;
import nl.edemtb.mtbclinicsapplication.mappers.UnregisteredUserMapper;
import nl.edemtb.mtbclinicsapplication.models.UnregisteredUser;
import nl.edemtb.mtbclinicsapplication.repositories.UnregisteredUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnregisteredUserService {

    private final UnregisteredUserRepository unregisteredUserRepository;
    private final UnregisteredUserMapper unregisteredUserMapper;

    public UnregisteredUserService(UnregisteredUserRepository unregisteredUserRepository, UnregisteredUserMapper unregisteredUserMapper) {
        this.unregisteredUserRepository = unregisteredUserRepository;
        this.unregisteredUserMapper = unregisteredUserMapper;
    }

    public List<UnregisteredUserDto> findAll() {
        List<UnregisteredUser> unregisteredUsers = unregisteredUserRepository.findAll();
        List<UnregisteredUserDto> dtos = new ArrayList<>();

        for (UnregisteredUser unregisteredUser : unregisteredUsers) {
            dtos.add(unregisteredUserMapper.transferToDto(unregisteredUser));
        }
        return dtos;
    }

    public UnregisteredUserDto findById(Long id) {
        Optional<UnregisteredUser> unregisteredUser = unregisteredUserRepository.findById(id);

        if (unregisteredUser.isPresent()) {
            return unregisteredUserMapper.transferToDto(unregisteredUser.get());
        } else {
            throw new RecordNotFoundException(" Unregistered user not found");
        }
    }

    public UnregisteredUserDto addUnregisteredUser(UnregisteredUserDto dto) {

        UnregisteredUser unregisteredUser = unregisteredUserMapper.transferToUnregisteredUser(dto);
        unregisteredUserRepository.save(unregisteredUser);
        return unregisteredUserMapper.transferToDto(unregisteredUser);
    }

    public Boolean deleteUnregisteredUser(Long id) {
        if (unregisteredUserRepository.existsById(id)) {
            unregisteredUserRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public UnregisteredUserDto updateUnregisteredUser(Long id, UnregisteredUserDto dto) {

        if (!unregisteredUserRepository.existsById(id)) {
            throw new RecordNotFoundException(" Unregistered user not found");
        }
        return unregisteredUserMapper.inputMapper(id, dto);
    }
}


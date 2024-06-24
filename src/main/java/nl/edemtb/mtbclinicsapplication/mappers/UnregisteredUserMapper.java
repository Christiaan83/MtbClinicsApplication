package nl.edemtb.mtbclinicsapplication.mappers;

import nl.edemtb.mtbclinicsapplication.dtos.UnregisteredUserDto;
import nl.edemtb.mtbclinicsapplication.models.UnregisteredUser;
import nl.edemtb.mtbclinicsapplication.repositories.UnregisteredUserRepository;
import org.springframework.stereotype.Component;

@Component
public class UnregisteredUserMapper {

    private final UnregisteredUserRepository unregisteredUserRepository;

    public UnregisteredUserMapper(UnregisteredUserRepository unregisteredUserRepository) {
        this.unregisteredUserRepository = unregisteredUserRepository;
    }

    public UnregisteredUserDto transferToDto(UnregisteredUser unregisteredUser) {

        UnregisteredUserDto dto = new UnregisteredUserDto();

        dto.setId(unregisteredUser.getId());
        dto.setFirstName(unregisteredUser.getFirstName());
        dto.setLastName(unregisteredUser.getLastName());
        dto.setEmail(unregisteredUser.getEmail());
        dto.setMobileNumber(unregisteredUser.getMobileNumber());

        return dto;
    }


    public UnregisteredUser transferToUnregisteredUser(UnregisteredUserDto dto) {
        UnregisteredUser unregisteredUser = new UnregisteredUser();

        unregisteredUser.setFirstName(dto.getFirstName());
        unregisteredUser.setLastName(dto.getLastName());
        unregisteredUser.setEmail(dto.getEmail());
        unregisteredUser.setMobileNumber(dto.getMobileNumber());

        return unregisteredUser;
    }

    public UnregisteredUserDto inputMapper(Long id, UnregisteredUserDto dto) {

        unregisteredUserRepository.findById(id);
        UnregisteredUser unregisteredUser = unregisteredUserRepository.findById(id).get();

        if (dto.getFirstName() != null) {
            unregisteredUser.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            unregisteredUser.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null) {
            unregisteredUser.setEmail(dto.getEmail());
        }
        if (dto.getMobileNumber() != null) {
            unregisteredUser.setMobileNumber(dto.getMobileNumber());
        }
        return transferToDto((unregisteredUserRepository.save(unregisteredUser)));
    }
}

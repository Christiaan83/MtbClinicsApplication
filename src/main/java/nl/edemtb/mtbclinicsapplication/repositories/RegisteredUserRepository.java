package nl.edemtb.mtbclinicsapplication.repositories;

import nl.edemtb.mtbclinicsapplication.models.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, String> {

}

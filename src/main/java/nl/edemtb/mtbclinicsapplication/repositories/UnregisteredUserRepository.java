package nl.edemtb.mtbclinicsapplication.repositories;


import nl.edemtb.mtbclinicsapplication.models.UnregisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnregisteredUserRepository extends JpaRepository<UnregisteredUser, Long> {
}

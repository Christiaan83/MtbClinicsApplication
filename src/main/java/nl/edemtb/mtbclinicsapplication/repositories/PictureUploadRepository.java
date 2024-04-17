package nl.edemtb.mtbclinicsapplication.repositories;

import nl.edemtb.mtbclinicsapplication.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PictureUploadRepository extends JpaRepository<Picture, String> {
    Optional<Picture> findByFileName(String filename);

}

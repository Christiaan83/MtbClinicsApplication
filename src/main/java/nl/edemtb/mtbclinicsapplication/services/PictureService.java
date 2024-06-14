package nl.edemtb.mtbclinicsapplication.services;

import nl.edemtb.mtbclinicsapplication.models.Picture;
import nl.edemtb.mtbclinicsapplication.repositories.PictureUploadRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class PictureService {

    private final Path fileStoragePath;
    private final String fileStorageLocation;
    private final PictureUploadRepository uploadRepository;

    public PictureService(@Value("${my.upload_location}") String fileStorageLocation, PictureUploadRepository uploadRepository) throws IOException {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();;
        this.fileStorageLocation = fileStorageLocation;
        this.uploadRepository = uploadRepository;

        Files.createDirectories(fileStoragePath);
    }

    public String storePicture(MultipartFile file) throws IOException{

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = fileStoragePath.resolve(fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        uploadRepository.save(new Picture(fileName));
        return fileName;
    }

    public Resource downLoadPicture(String fileName) {

        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if(resource.exists()&& resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("the file doesn't exist or not readable");
        }
    }

}

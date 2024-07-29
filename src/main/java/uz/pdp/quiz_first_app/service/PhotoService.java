package uz.pdp.quiz_first_app.service;

import lombok.*;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.settings.PhotoDTO;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class PhotoService {

    private String photoFilePath;
    private Path photosPath;

    @PostConstruct
    public void init() {
        String currentDir = System.getProperty("user.dir");
        photoFilePath = currentDir + "/files/photos";
        photosPath = Paths.get(photoFilePath).toAbsolutePath().normalize();
    }

    public ResponseEntity<?> getAllPhotos() {
        List<PhotoDTO> photos = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(photosPath)) {
            for (Path filePath : stream) {
                if (Files.isRegularFile(filePath)) {
                    String filename = filePath.getFileName().toString();
                    String url = "/api/photos/" + filename;
                    byte[] fileContent = Files.readAllBytes(filePath);
                    String encodedContent = Base64.getEncoder().encodeToString(fileContent);
                    photos.add(new PhotoDTO(filename, url, encodedContent));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not list the files", e);
        }
        return ResponseEntity.ok(photos);
    }

    @SneakyThrows
    public ResponseEntity<?> getPhoto(String photoName) {
        Path filePath = photosPath.resolve(photoName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            byte[] fileContent = Files.readAllBytes(filePath);
            String encodedContent = Base64.getEncoder().encodeToString(fileContent);
            PhotoDTO photoDTO = new PhotoDTO(photoName, "/api/photos/" + photoName, encodedContent);
            return ResponseEntity.ok(photoDTO);
        } else {
            throw new RuntimeException("Photo not found: " + photoName);
        }
    }

}

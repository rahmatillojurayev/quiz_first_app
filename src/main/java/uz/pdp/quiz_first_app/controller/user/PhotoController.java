package uz.pdp.quiz_first_app.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.quiz_first_app.service.PhotoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/photo")
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping
    public ResponseEntity<?> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping("/{photoName}")
    public ResponseEntity<?> getPhotoByName(@PathVariable String photoName) {
        return photoService.getPhoto(photoName);
    }

}

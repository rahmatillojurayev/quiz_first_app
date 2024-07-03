package uz.pdp.quiz_first_app.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.quiz_first_app.dto.StatusDTO;
import uz.pdp.quiz_first_app.repo.UserRepo;
import uz.pdp.quiz_first_app.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserRepo userRepo;
    private final UserService userService;

    @GetMapping("/home")
    public String home() {
        return "";
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PostMapping("/status")
    public ResponseEntity<?> changeUserStatus(@RequestBody StatusDTO statusDTO){
        return userService.changeUserStatus(statusDTO.getStatus());
    }

}

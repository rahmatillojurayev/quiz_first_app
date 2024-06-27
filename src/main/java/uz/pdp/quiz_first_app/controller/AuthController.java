package uz.pdp.quiz_first_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.quiz_first_app.dto.ConfirmationDTO;
import uz.pdp.quiz_first_app.dto.LoginDTO;
import uz.pdp.quiz_first_app.dto.RegisterDTO;
import uz.pdp.quiz_first_app.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return authService.logInAndReturnToken(loginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        return authService.registerService(registerDTO);
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(
            @RequestHeader("Registration-token") String token,
            @RequestBody ConfirmationDTO confirmationDTO
    ) {
        return authService.confirmVerification(token, confirmationDTO);
    }

}

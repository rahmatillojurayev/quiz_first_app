package uz.pdp.quiz_first_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.quiz_first_app.dto.*;
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
            @RequestBody ConfirmationDTO confirmationDTO) {
        return authService.confirmVerification(token, confirmationDTO);
    }

    @PostMapping("/forget_password")
    public ResponseEntity<?> refreshToken(@RequestBody RegisterDTO registerDTO) {
        return authService.forgetPassword(registerDTO);
    }

    @PostMapping("/reset_password_confirm")
    public ResponseEntity<?> resetPassword(
            @RequestHeader("Reset-password-token") String token,
            @RequestBody ResetPasswordDTO resetPasswordDTO) {
        return authService.resetPassword(token, resetPasswordDTO);
    }

    @PostMapping("/reset_new_password")
    public ResponseEntity<?> refreshToken(@RequestHeader("Reset-email-token") String emailToken,
                                          @RequestBody ForgetConfirmDTO forgetConfirmDTO) {
        return authService.resetNewPassword(emailToken, forgetConfirmDTO);
    }

}

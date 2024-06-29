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
    public ResponseEntity<?> register(@RequestBody RegisterReq registerReq) {
        return authService.registerService(registerReq);
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(
            @RequestHeader("Registration-token") String token,
            @RequestBody ConfirmationDTO confirmationDTO) {
        return authService.confirmVerification(token, confirmationDTO);
    }

    @PostMapping("/forget-password")
    public ResponseEntity<?> refreshToken(@RequestBody EmailDTO email) {
        return authService.forgetPassword(email);
    }

    @PostMapping("/forget-password-confirm")
    public ResponseEntity<?> resetPasswordConfirmation(
            @RequestHeader("Reset-password-token") String token,
            @RequestBody ResetPasswordDTO resetPasswordDTO) {
        return authService.resetPasswordConfirm(token, resetPasswordDTO);
    }

    @PostMapping("/update-password")
    public ResponseEntity<?> resetPassword(
            @RequestHeader("Reset-email-token") String token,
            @RequestBody PasswordDTO newPassword
    ) {
        return authService.resetPasswordAndUpdate(token, newPassword);
    }

}

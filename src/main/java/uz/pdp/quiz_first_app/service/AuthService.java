package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.auth.*;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.repo.UserRepo;
import uz.pdp.quiz_first_app.config.security.JwtUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;
    private final EmailService emailService;
    private final MessageService messageService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> registerService(RegisterReq registerReq) {
        if (userRepo.existsByEmail(registerReq.getEmail())) {
            String message = messageService.getMessage("user.already.exists");
            return ResponseEntity.status(400).body(message);
        } else {
            Integer code = emailService.genRandCode();
            emailService.sendEmailConfirmCode(registerReq.getEmail(), code);
            String token = jwtUtil.generateRegistrationToken(registerReq, code);
            String message = messageService.getMessage("verification.code.sent");
            return ResponseEntity.ok().body(new RegisterResponse(message, token));
        }
    }

    public ResponseEntity<?> confirmVerification(String token, ConfirmationDTO confirmationDTO) {
        String actualCode = jwtUtil.getConfirmationCodeFromToken(token);
        String email = jwtUtil.getEmailFromToken(token);
        if (actualCode.equals(confirmationDTO.getVerificationCode()) && confirmationDTO.getEmail().equals(email)) {
            String message = messageService.getMessage("verification.confirm.success");
            userService.saveUser(token);
            return ResponseEntity.ok().body(message);
        } else {
            String message = messageService.getMessage("verification.confirm.failed");
            return ResponseEntity.status(400).body(message);
        }
    }

    public ResponseEntity<?> logInAndReturnToken(LoginDTO loginDTO) {
        try {
            UserDetails userDetails = userService.getUserDetails(loginDTO.getEmail(), loginDTO.getPassword());
            String lang = LocaleContextHolder.getLocale().getLanguage();
            TokenDTO tokenDTO = new TokenDTO(
                    "Bearer " + jwtUtil.generateToken(userDetails, lang),
                    "Bearer " + jwtUtil.generateRefreshToken(userDetails, lang)
            );
            return ResponseEntity.ok().body(tokenDTO);
        } catch (AuthenticationException e) {
            String message = messageService.getMessage("login.failed");
            return ResponseEntity.status(401).body(message);
        }
    }

    public ResponseEntity<?> forgetPassword(EmailDTO email) {
        if (!userRepo.existsByEmail(email.getEmail())) {
            String message = messageService.getMessage("email.not.found");
            return ResponseEntity.status(400).body(message);
        } else {
            Integer code = emailService.genRandCode();
            emailService.sendEmailConfirmCode(email.getEmail(), code);
            String message = messageService.getMessage("verification.code.sent");
            String token = jwtUtil.generateForgetPasswordToken(email.getEmail(), code);
            return ResponseEntity.ok().body(new RegisterResponse(message, token));
        }
    }

    public ResponseEntity<?> resetPasswordConfirm(String token, ResetPasswordDTO resetPasswordDTO) {
        String actualCode = jwtUtil.getConfirmationCodeFromToken(token);
        String emailFromToken = jwtUtil.getEmailFromToken(token);
        if (actualCode.equals(resetPasswordDTO.getVerificationCode()) && resetPasswordDTO.getEmail().equals(emailFromToken)) {
            String message = messageService.getMessage("verification.confirm.success");
            String resetPasswordToken = jwtUtil.generateResetPasswordToken(emailFromToken);
            return ResponseEntity.ok().body(
                    new ResetPasswordResp(
                            message, resetPasswordToken
                    )
            );
        } else {
            String message = messageService.getMessage("verification.confirm.failed");
            return ResponseEntity.status(400).body(message);
        }
    }

    public ResponseEntity<?> resetPasswordAndUpdate(String token, PasswordDTO newPassword) {
        String emailFromToken = jwtUtil.getEmailFromToken(token);
        User user = userRepo.findByEmail(emailFromToken).orElseThrow();
        if (passwordEncoder.matches(newPassword.getPassword(), user.getPassword())) {
            String message = messageService.getMessage("new.password.must.not.be.similar");
            return ResponseEntity.status(400).body(message);
        } else {
            String message = messageService.getMessage("reset.password.success");
            userService.editUserPassword(emailFromToken, newPassword.getPassword());
            return ResponseEntity.ok().body(message);
        }
    }

    public ResponseEntity<?> validateAndReturnTokens(String refreshToken) {
        if (jwtUtil.validateToken(refreshToken)) {
            String email = jwtUtil.getUsername(refreshToken);
            String lang = jwtUtil.getLang(refreshToken);
            Optional<User> userOptional = userRepo.findByEmail(email);
            if(userOptional.isPresent()) {
                UserDetails userDetails = userOptional.get();
                TokenDTO tokenDTO = new TokenDTO(
                        "Bearer " + jwtUtil.generateToken(userDetails, lang),
                        "Bearer " + jwtUtil.generateRefreshToken(userDetails, lang)
                );
                return ResponseEntity.ok().body(tokenDTO);
            }
            return ResponseEntity.badRequest().body("User not found");
        }else{
            return ResponseEntity.badRequest().body("Invalid refresh token");
        }
    }

}

package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.*;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.repo.UserRepo;
import uz.pdp.quiz_first_app.security.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;
    private final EmailService emailService;
    private final MessageService messageService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> registerService(RegisterDTO registerDTO) {
        if (userRepo.existsByEmail(registerDTO.getEmail())) {
            String message = messageService.getMessage("user.already.exists");
            return ResponseEntity.status(400).body(message);
        } else {
            emailService.sendEmailConfirmCode(registerDTO);
            String token = jwtUtil.generateRegistrationToken(registerDTO);
            String message = messageService.getMessage("verification.code.sent");
            return ResponseEntity.ok().body(new RegisterResponse(message, token));
        }
    }

    public ResponseEntity<String> confirmVerification(String token, ConfirmationDTO confirmationDTO) {
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
            var token = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
            Authentication authenticate = authenticationManager.authenticate(token);
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
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

    public ResponseEntity<?> forgetPassword(RegisterDTO registerDTO) {
        if (!userRepo.existsByEmail(registerDTO.getEmail())) {
            String message = messageService.getMessage("email.not.found");
            return ResponseEntity.status(400).body(message);
        } else {
            emailService.sendEmailConfirmCode(registerDTO);
            String message = messageService.getMessage("verification.code.sent");
            String token = jwtUtil.generateForgetPasswordToken(registerDTO);
            return ResponseEntity.ok().body(new RegisterResponse(message, token));
        }
    }

    public ResponseEntity<?> resetPassword(String token, ResetPasswordDTO resetPasswordDTO) {
        String actualCode = jwtUtil.getConfirmationCodeFromToken(token);
        String emailFromToken = jwtUtil.getEmailFromToken(token);
        if (actualCode.equals(resetPasswordDTO.getVerificationCode()) && resetPasswordDTO.getEmail().equals(emailFromToken)) {
            User user = userRepo.findByEmail(emailFromToken).orElseThrow();
            if (passwordEncoder.matches(resetPasswordDTO.getNewPassword(), user.getPassword())) {
                String message = messageService.getMessage("new.password.must.not.be.similar");
                return ResponseEntity.status(400).body(message);
            } else {
                String message = messageService.getMessage("reset.password.success");
                userService.editUserPassword(emailFromToken, resetPasswordDTO.getNewPassword());
                return ResponseEntity.ok().body(message);
            }
        } else {
            String message = messageService.getMessage("reset.password.fail");
            return ResponseEntity.status(400).body(message);
        }
    }


}

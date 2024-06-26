package uz.pdp.quiz_first_app.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import uz.pdp.quiz_first_app.dto.LoginDTO;
import uz.pdp.quiz_first_app.dto.RegisterDTO;
import uz.pdp.quiz_first_app.dto.TokenDTO;
import uz.pdp.quiz_first_app.security.JwtUtil;
import uz.pdp.quiz_first_app.service.UserService;
import java.util.Locale;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final MessageSource messageSource;

    @PostMapping("/login")
    public TokenDTO login(@RequestBody LoginDTO loginDTO) {
        var token = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
        String lang = LocaleContextHolder.getLocale().getLanguage();
        return new TokenDTO(
                "Bearer " + jwtUtil.generateToken(userDetails, lang),
               "Bearer " + jwtUtil.generateRefreshToken(userDetails, lang)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println(locale.getLanguage());
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            String message = messageSource.getMessage("passwords-do-not-match", null, locale);
            return ResponseEntity.status(400).body(message);
        }else if (userService.register(registerDTO)) {
            String message = messageSource.getMessage("register-success", null, locale);
            return ResponseEntity.ok(message);
        }else {
            String message = messageSource.getMessage("user-already-exists", null, locale);
            return ResponseEntity.status(400).body(message);
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestBody RegisterDTO registerDTO) {
        Locale locale = LocaleContextHolder.getLocale();
        if (userService.confirm(registerDTO)) {
            String message = messageSource.getMessage("verification-confirm-success", null, locale);
            return ResponseEntity.ok(message);
        }else {
            String message = messageSource.getMessage("verification-confirm-failed", null, locale);
            return ResponseEntity.status(400).body(message);
        }
    }

}

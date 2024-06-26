package uz.pdp.quiz_first_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.quiz_first_app.dto.LoginDTO;
import uz.pdp.quiz_first_app.dto.RegisterDTO;
import uz.pdp.quiz_first_app.dto.TokenDTO;
import uz.pdp.quiz_first_app.repo.UserRepository;
import uz.pdp.quiz_first_app.security.JwtUtil;
import uz.pdp.quiz_first_app.service.EmailService;
import uz.pdp.quiz_first_app.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;
    private final UserRepository userRepository;


    @PostMapping("/login")
    public TokenDTO login(@RequestBody LoginDTO loginDTO) {
        var token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
        return new TokenDTO(
                "Bearer " + jwtUtil.generateToken(userDetails),
               "Bearer " + jwtUtil.generateRefreshToken(userDetails)
        );
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        if (userService.register(registerDTO)) {
            return ResponseEntity.ok("You have successfully registered. Check email confirm code");
        }else {
            return ResponseEntity.status(400).body("Username already exists");
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestBody RegisterDTO registerDTO) {
        if (userService.confirm(registerDTO)) {
            return ResponseEntity.ok("You have successfully confirmed");
        }else {
            return ResponseEntity.status(400).body("Invalid confirm code");
        }
    }

}

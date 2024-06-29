package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.TokenDTO;
import uz.pdp.quiz_first_app.dto.UsernameDTO;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.entity.enums.RoleName;
import uz.pdp.quiz_first_app.repo.RoleRepo;
import uz.pdp.quiz_first_app.repo.UserRepo;
import uz.pdp.quiz_first_app.security.CustomUserDetailsService;
import uz.pdp.quiz_first_app.security.JwtUtil;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;
    private final AuthenticationManager authenticationManager;
    private final MessageService messageService;

    public TokenDTO updateUserLanguage(String lang) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return new TokenDTO(
                "Bearer " + jwtUtil.generateToken(userDetails, lang),
                "Bearer " + jwtUtil.generateRefreshToken(userDetails, lang)
        );
    }

    public void saveUser(String token) {
        String email = jwtUtil.getEmailFromToken(token);
        String password = jwtUtil.getPasswordFromToken(token);
        String username = email.split("@")[0];
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .username(username)
                .roles(List.of(roleRepo.findByRoleName(RoleName.ROLE_USER)))
                .build();
        userRepo.save(user);
    }

    public void editUserPassword(String email, String newPassword) {
        User user = userRepo.findByEmail(email).orElseThrow();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }

    public UserDetails getUserDetails(String email, String password) {
        var token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authenticate = authenticationManager.authenticate(token);
        return (UserDetails) authenticate.getPrincipal();
    }

    public ResponseEntity<?> editUsername(UsernameDTO usernameDTO) {
        String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.findByEmail(email).orElseThrow();
        user.setUsername(usernameDTO.getUsername());
        userRepo.save(user);
        String message = messageService.getMessage("username.edited");
        return ResponseEntity.ok(message);
    }

    public List<User> getAllUsers() {
        return userRepo.findAllByIsStartedIsTrue();
    }
}

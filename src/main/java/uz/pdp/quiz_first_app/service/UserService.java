package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.TokenDTO;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.entity.enums.RoleName;
import uz.pdp.quiz_first_app.repo.RoleRepository;
import uz.pdp.quiz_first_app.repo.UserRepository;
import uz.pdp.quiz_first_app.security.CustomUserDetailsService;
import uz.pdp.quiz_first_app.security.JwtUtil;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

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
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(List.of(roleRepository.findByRoleName(RoleName.ROLE_USER)))
                .build();
        userRepository.save(user);
    }

}

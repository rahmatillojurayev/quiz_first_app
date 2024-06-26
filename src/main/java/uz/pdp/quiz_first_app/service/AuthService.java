package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.TokenDTO;
import uz.pdp.quiz_first_app.security.CustomUserDetailsService;
import uz.pdp.quiz_first_app.security.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public TokenDTO updateUserLanguage(String lang) {
        System.out.println("lang = " + lang);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return new TokenDTO(
                "Bearer " + jwtUtil.generateToken(userDetails, lang),
                "Bearer " + jwtUtil.generateRefreshToken(userDetails, lang)
        );
    }

}

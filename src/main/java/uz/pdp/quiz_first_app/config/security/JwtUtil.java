package uz.pdp.quiz_first_app.config.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import uz.pdp.quiz_first_app.dto.auth.RegisterReq;
import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "1234567812345678123456781234567812345678123456781234567812345678";
    private static final long ACCESS_TOKEN_VALIDITY = 1000 * 60 * 60 * 24;
    private static final long REFRESH_TOKEN_VALIDITY = 1000 * 60 * 60 * 24 * 7;

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            throw new JwtException("Invalid JWT token", e);
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getKey() {
        byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String getLang(String token) {
        Claims claims = getClaims(token);
        return claims.get("lang", String.class);
    }

    public List<GrantedAuthority> getRoles(String token) {
        String roles = getClaims(token).get("roles", String.class);
        return Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public String generateToken(UserDetails userDetails, String lang) {
        return generateToken(userDetails, lang, ACCESS_TOKEN_VALIDITY);
    }

    public String generateRefreshToken(UserDetails userDetails, String lang) {
        return generateToken(userDetails, lang, REFRESH_TOKEN_VALIDITY);
    }

    private String generateToken(UserDetails userDetails, String lang, long validity) {
        String roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + validity))
                .signWith(getKey())
                .claim("roles", roles)
                .claim("lang", lang)
                .compact();
    }

    public String generateRegistrationToken(RegisterReq registerReq, Integer code) {
        return Jwts.builder()
                .subject(registerReq.getEmail())
                .claim("confirmationCode", code.toString())
                .claim("password", registerReq.getPassword())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(getKey())
                .compact();
    }

    public String getConfirmationCodeFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.get("confirmationCode", String.class);
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public String getPasswordFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.get("password", String.class);
    }

    public String generateForgetPasswordToken(String email, Integer code) {
        return Jwts.builder()
                .subject(email)
                .claim("confirmationCode", code.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(getKey())
                .compact();
    }

    public String generateResetPasswordToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(getKey())
                .compact();
    }

}

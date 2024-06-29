package uz.pdp.quiz_first_app.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenService {

    private final Set<String> invalidatedTokens = new HashSet<>();

    public void invalidateToken(String token) {
        invalidatedTokens.add(token);
    }

    public boolean isTokenValid(String token) {
        return !invalidatedTokens.contains(token);
    }
}

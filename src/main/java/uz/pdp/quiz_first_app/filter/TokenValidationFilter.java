package uz.pdp.quiz_first_app.filter;


import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.quiz_first_app.service.TokenService;


public class TokenValidationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public TokenValidationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && tokenService.isTokenValid(token)) {
            filterChain.doFilter(request, response);
        } else {
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing token");
        }
    }
}

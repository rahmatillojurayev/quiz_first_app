package uz.pdp.quiz_first_app.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.LocaleResolver;
import uz.pdp.quiz_first_app.security.JwtUtil;
import java.util.Locale;
import java.util.Objects;

@RequiredArgsConstructor
public class TokenLocaleResolver implements LocaleResolver {

    private final JwtUtil jwtUtil;
    private static final String DEFAULT_LANGUAGE = "en";

    @SuppressWarnings("NullableProblems")
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = extractLanguageFromToken(request);
        return (language != null) ? Locale.forLanguageTag(language) : Locale.forLanguageTag(DEFAULT_LANGUAGE);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        LocaleContextHolder.setLocale(locale);
        Objects.requireNonNull(response).setLocale(locale);
    }

    private String extractLanguageFromToken(HttpServletRequest request) {
        String token = getJwtFromRequest(request);
        if (token != null && jwtUtil.validateToken(token)) {
            return jwtUtil.getLang(token);
        }
        return null;
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}

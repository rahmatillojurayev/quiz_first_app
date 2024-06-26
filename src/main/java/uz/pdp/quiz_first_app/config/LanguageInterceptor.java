package uz.pdp.quiz_first_app.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import java.util.Locale;

public class LanguageInterceptor implements HandlerInterceptor {

    private static final String LANGUAGE_HEADER = "Accept-Language";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver != null && request.getHeader("Authorization") == null) {
            String lang = request.getHeader(LANGUAGE_HEADER);
            if (lang != null && !lang.isEmpty()) {
                localeResolver.setLocale(request, response, Locale.forLanguageTag(lang));
            }
        }
        return true;
    }
}

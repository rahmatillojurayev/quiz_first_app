package uz.pdp.quiz_first_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final MessageSource messageSource;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("test", null, locale);
        return ResponseEntity.ok(message);
    }

}

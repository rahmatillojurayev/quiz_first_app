package uz.pdp.quiz_first_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.quiz_first_app.dto.TokenDTO;
import uz.pdp.quiz_first_app.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/settings")
public class SettingsController {

    private final AuthService authService;

    @PostMapping("/lang/{lang}")
    public ResponseEntity<?> updateLanguage(@PathVariable String lang) {
        TokenDTO tokenDTO = authService.updateUserLanguage(lang);
        return ResponseEntity.ok().body(tokenDTO);
    }

}

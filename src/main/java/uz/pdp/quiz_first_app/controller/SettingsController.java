package uz.pdp.quiz_first_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.quiz_first_app.dto.TokenDTO;
import uz.pdp.quiz_first_app.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/settings")
public class SettingsController {

    private final UserService userService;

    @PostMapping("/lang/{lang}")
    public ResponseEntity<?> updateLanguage(@PathVariable String lang) {
        TokenDTO tokenDTO = userService.updateUserLanguage(lang);
        return ResponseEntity.ok().body(tokenDTO);
    }

}

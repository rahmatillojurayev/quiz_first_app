package uz.pdp.quiz_first_app.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.quiz_first_app.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refresh")
public class RefreshController {

    private final AuthService authService;

    @GetMapping("/{refreshToken}")
    public ResponseEntity<?> refreshToken(@PathVariable String refreshToken) {
        return authService.validateAndReturnTokens(refreshToken);
    }

}

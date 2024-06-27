package uz.pdp.quiz_first_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.quiz_first_app.service.MessageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final MessageService messageService;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        String message = messageService.getMessage("test");
        return ResponseEntity.ok(message);
    }

}

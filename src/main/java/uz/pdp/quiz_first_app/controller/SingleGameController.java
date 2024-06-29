package uz.pdp.quiz_first_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.quiz_first_app.dto.SingleGameDTO;
import uz.pdp.quiz_first_app.service.SingleGameService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/single-game")
public class SingleGameController {

    private final SingleGameService singleGameService;

    @PostMapping("/create")
    public ResponseEntity<?> createSingleGame(@RequestBody SingleGameDTO singleGameDTO) {
        return singleGameService.createAndSave(singleGameDTO);
    }

}

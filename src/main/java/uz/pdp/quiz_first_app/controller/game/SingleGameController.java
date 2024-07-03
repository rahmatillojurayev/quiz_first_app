package uz.pdp.quiz_first_app.controller.game;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.quiz_first_app.dto.game.SingleGameDTO;
import uz.pdp.quiz_first_app.entity.Category;
import uz.pdp.quiz_first_app.service.SingleGameService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/single-game")
public class SingleGameController {

    private final SingleGameService singleGameService;


    @GetMapping("/start")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = singleGameService.getAllCategories();
        return ResponseEntity.ok(categories);

    }

    @PostMapping("/create")
    public ResponseEntity<?> createSingleGame(@RequestBody SingleGameDTO singleGameDTO) {
        return singleGameService.createAndSave(singleGameDTO);
    }

}

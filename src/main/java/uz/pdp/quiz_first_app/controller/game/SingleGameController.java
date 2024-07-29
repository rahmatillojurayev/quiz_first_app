package uz.pdp.quiz_first_app.controller.game;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.quiz_first_app.dto.game.SingleGameDTO;
import uz.pdp.quiz_first_app.entity.Category;
import uz.pdp.quiz_first_app.service.SingleGameService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/single-game")
public class SingleGameController {

    private final SingleGameService singleGameService;

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = singleGameService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/start/{categoryId}")
    public ResponseEntity<?> getQuestions(@PathVariable int categoryId) {
        return singleGameService.getQuestions(categoryId);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSingleGame(@RequestBody SingleGameDTO singleGameDTO) {
        return singleGameService.createAndSave(singleGameDTO);
    }

}

package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.game.SingleGameDTO;
import uz.pdp.quiz_first_app.entity.*;
import uz.pdp.quiz_first_app.repo.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SingleGameService {

    private final SingleGameRepo singleGameRepo;
    private final CategoryRepo categoryRepo;
    private final UserService userService;
    private final QuestionService questionService;

    public ResponseEntity<?> createAndSave(SingleGameDTO singleGameDTO) {
        User currentUser = userService.getCurrentUser();
        SingleGame singleGame = SingleGame.builder()
                .user(currentUser)
                .score(singleGameDTO.getScore())
                .correctAnswers(singleGameDTO.getCorrectAnswers())
                .wrongAnswers(singleGameDTO.getWrongAnswers())
                .build();
        singleGameRepo.save(singleGame);
        return ResponseEntity.ok(singleGame);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public ResponseEntity<?> getQuestions(Integer categoryId) {
        return questionService.generateQuestions(categoryId);
    }

}

package uz.pdp.quiz_first_app.controller.game;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.quiz_first_app.service.QuestionService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryQuestions(@PathVariable Integer categoryId){
        return questionService.generateQuestions(categoryId);
    }

}

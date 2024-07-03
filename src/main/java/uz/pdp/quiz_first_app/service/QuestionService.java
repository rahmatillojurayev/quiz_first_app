package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.game.QuestionDTO;
import uz.pdp.quiz_first_app.entity.Question;
import uz.pdp.quiz_first_app.repo.QuestionRepo;
import uz.pdp.quiz_first_app.util.QuestionUtil;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepo questionRepo;
    private final QuestionUtil questionUtil;

    public ResponseEntity<?> generateQuestions(Integer categoryId) {
        List<QuestionDTO> questionDTOS = getRandomQuestions(categoryId);
        return ResponseEntity.ok(questionDTOS);
    }

    public List<QuestionDTO> getRandomQuestions(Integer categoryId) {
        List<Question> questions = questionRepo.findRandomQuestionsByCategoryId(categoryId);
        return questionUtil.makeQuestionDTOList(questions);
    }

}

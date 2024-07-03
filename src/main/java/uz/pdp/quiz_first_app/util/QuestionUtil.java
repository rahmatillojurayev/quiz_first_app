package uz.pdp.quiz_first_app.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.game.QuestionDTO;
import uz.pdp.quiz_first_app.entity.Question;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionUtil {

    private final OptionUtil optionUtil;

    public List<QuestionDTO> makeQuestionDTOList(List<Question> questions) {
        String categoryName = questions.get(0).getCategory().getName();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            QuestionDTO questionDTO = new QuestionDTO(
                    question.getId(),
                    getTextFromLanguage(question),
                    categoryName,
                    i + 1,
                    optionUtil.getOptionsByQuestionId(question.getId())
            );
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    private String getTextFromLanguage(Question question) {
        String language = LocaleContextHolder.getLocale().getLanguage();
        return switch (language){
            case "uz" -> question.getTextUz();
            case "ru" -> question.getTextRu();
            default -> question.getTextEn();
        };
    }

}

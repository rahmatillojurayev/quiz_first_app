package uz.pdp.quiz_first_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private Integer gameId;
    private Integer userId;
    private String answer;
    private boolean isCorrect;
}

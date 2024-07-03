package uz.pdp.quiz_first_app.dto.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResultDTO {

    private UUID playerId;
    private Integer questionId;
    private Integer selectedOptionId;
    private boolean isCorrect;

}
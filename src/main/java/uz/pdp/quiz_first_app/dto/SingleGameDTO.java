package uz.pdp.quiz_first_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleGameDTO {

    private Integer score;

    private Integer correctAnswers;

    private Integer wrongAnswers;

}

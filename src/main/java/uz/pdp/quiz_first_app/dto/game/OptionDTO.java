package uz.pdp.quiz_first_app.dto.game;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionDTO {

    private Integer id;

    private String text;

    private char option;

    private boolean isCorrect;

}

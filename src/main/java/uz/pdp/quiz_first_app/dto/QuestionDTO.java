package uz.pdp.quiz_first_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDTO {

    private Integer questionId;

    private String text;

    private String categoryName;

    private Integer order;

    private List<OptionDTO> options;

}

package uz.pdp.quiz_first_app.dto.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.quiz_first_app.projection.HistoryProjection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchHistoryDTO {

    private Integer totalScore;

    private List<HistoryProjection> histories;

}

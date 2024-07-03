package uz.pdp.quiz_first_app.dto.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameSessionDTO {

    private Integer id;

    private UUID player1Id;

    private UUID player2Id;

    private List<QuestionDTO> questions;

}

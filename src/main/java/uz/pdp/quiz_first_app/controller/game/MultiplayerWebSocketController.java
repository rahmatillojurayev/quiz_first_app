package uz.pdp.quiz_first_app.controller.game;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import uz.pdp.quiz_first_app.dto.game.AnswerSelectionMessageDTO;
import uz.pdp.quiz_first_app.service.MultiplayerService;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MultiplayerWebSocketController {

    private final MultiplayerService multiplayerService;

    @MessageMapping("/search")
    public void searchForMatch(UUID playerId) {
        multiplayerService.searchOpponent(playerId);
    }

    @MessageMapping("/answer")
    public void answerQuestion(AnswerSelectionMessageDTO answer) {
        multiplayerService.handleAnswer(answer);
    }

}

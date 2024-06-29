package uz.pdp.quiz_first_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.quiz_first_app.dto.AnswerDTO;
import uz.pdp.quiz_first_app.entity.MultiPlayer;
import uz.pdp.quiz_first_app.service.MultiPlayerService;

@RestController
@RequiredArgsConstructor
public class QuizWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    private final MultiPlayerService multiPlayerService;

    @MessageMapping("/answer")
    @SendTo("/topic/answers/{gameId}")
    public AnswerDTO handleAnswer(AnswerDTO answerDTO) {
        MultiPlayer multiPlayer = multiPlayerService.getGameById(answerDTO.getGameId()).orElseThrow();
        multiPlayer.setCorrectAnswers(multiPlayer.getCorrectAnswers() + (answerDTO.isCorrect() ? 1 : 0));
        multiPlayer.setWrongAnswers(multiPlayer.getWrongAnswers() + (answerDTO.isCorrect() ? 0 : 1));
        multiPlayerService.updateGame(multiPlayer);
        messagingTemplate.convertAndSend("/topic/answers/" + answerDTO.getGameId(), answerDTO);
        return answerDTO;
    }
}

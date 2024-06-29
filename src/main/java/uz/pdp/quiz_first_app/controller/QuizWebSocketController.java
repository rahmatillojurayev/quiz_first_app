package uz.pdp.quiz_first_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.quiz_first_app.dto.AnswerDTO;
import uz.pdp.quiz_first_app.entity.MultiPlayer;
import uz.pdp.quiz_first_app.service.MultiPlayerService;

@RestController
public class QuizWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MultiPlayerService multiPlayerService;

    @MessageMapping("/answer")
    @SendTo("/topic/answers")
    public AnswerDTO handleAnswer(AnswerDTO answerDTO) {
        // Process the answer, e.g., update the score and save it in the database
        MultiPlayer multiPlayer = multiPlayerService.getGameById(answerDTO.getGameId()).orElseThrow();
        // Update the game with the new answer details (example)
        multiPlayer.setCorrectAnswers(multiPlayer.getCorrectAnswers() + (answerDTO.isCorrect() ? 1 : 0));
        multiPlayer.setWrongAnswers(multiPlayer.getWrongAnswers() + (answerDTO.isCorrect() ? 0 : 1));
        multiPlayerService.updateGame(multiPlayer);
        
        // Notify the users about the new answer
        messagingTemplate.convertAndSend("/topic/answers", answerDTO);

        return answerDTO;
    }
}

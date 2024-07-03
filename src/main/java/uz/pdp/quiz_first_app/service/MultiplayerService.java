package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.game.*;
import uz.pdp.quiz_first_app.entity.*;
import uz.pdp.quiz_first_app.repo.*;
import java.util.*;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class MultiplayerService {

    private final Queue<UUID> waitingPlayers = new ConcurrentLinkedQueue<>();
    private final Map<UUID, GameSession> activeGames = new ConcurrentHashMap<>();

    private final UserRepo userRepo;
    private final GameSessionRepo gameSessionRepo;
    private final QuestionService questionService;
    private final SimpMessagingTemplate messagingTemplate;
    private final PlayerGameSessionService playerGameSessionService;
    private final PlayerGameSessionRepo playerGameSessionRepo;
    private final OptionRepo optionRepo;

    private final Random random = new Random();
    private final UserService userService;
    private final GameSessionService gameSessionService;

    public void searchOpponent(UUID playerId) {
        synchronized (waitingPlayers) {
            if (!waitingPlayers.contains(playerId)) {
                waitingPlayers.add(playerId);
            }
            if (waitingPlayers.size() >= 2) {
                UUID player1Id = waitingPlayers.poll();
                UUID player2Id = waitingPlayers.poll();

                if (player1Id != null && player2Id != null) {
                    matchPlayers(player1Id, player2Id);
                }
            }
        }
    }

    private void matchPlayers(UUID player1Id, UUID player2Id) {
        User player1 = userRepo.findById(player1Id).orElseThrow(() -> new RuntimeException("User not found"));
        User player2 = userRepo.findById(player2Id).orElseThrow(() -> new RuntimeException("User not found"));

        player1.setUserStatus("IN_GAME");
        player2.setUserStatus("IN_GAME");
        userRepo.save(player1);
        userRepo.save(player2);

        PlayerGameSession player1GameSession = playerGameSessionService.createPlayerGameSession(player1);
        PlayerGameSession player2GameSession = playerGameSessionService.createPlayerGameSession(player2);

        GameSession gameSession = GameSession.builder()
                .player1GameSessionId(player1GameSession.getId())
                .player2GameSessionId(player2GameSession.getId())
                .status("ONGOING")
                .build();

        GameSession savedGame = gameSessionRepo.save(gameSession);
        activeGames.put(player1Id, savedGame);
        activeGames.put(player2Id, savedGame);

        GameSessionDTO gameSessionDTO = new GameSessionDTO(
                savedGame.getGameId(),
                player1Id,
                player2Id,
                questionService.getRandomQuestions(random.nextInt(1, 5))
        );
        notifyPlayersOfMatch(player1Id, player2Id, gameSessionDTO);
    }

    public void handleAnswer(AnswerSelectionMessageDTO answer) {
        UUID playerId = answer.getPlayerId();
        Integer questionId = answer.getQuestionId();
        Integer selectedOptionId = answer.getSelectedOptionId();

        GameSession gameSession = activeGames.get(playerId);
        if (gameSession != null) {

            Option selectedOption = optionRepo.findById(selectedOptionId)
                    .orElseThrow(() -> new RuntimeException("Option not found"));

            boolean isCorrect = selectedOption.isCorrect();

            if(isCorrect){
                playerGameSessionService.updatePlayerResults(playerId);
            }

            AnswerResultDTO answerResultDTO = new AnswerResultDTO(playerId, questionId, selectedOptionId, isCorrect);
            notifyOpponentOfAnswer(answerResultDTO);
        }
    }

    private UUID getOpponentId(UUID playerId, GameSession gameSession) {
        var player1GameSession = playerGameSessionRepo.findById(gameSession.getPlayer1GameSessionId()).orElseThrow();
        var player2GameSession = playerGameSessionRepo.findById(gameSession.getPlayer2GameSessionId()).orElseThrow();

        UUID player1Id = player1GameSession.getUser().getId();
        UUID player2Id = player2GameSession.getUser().getId();

        return player1Id.equals(playerId) ? player2Id : player1Id;
    }

    private void notifyPlayersOfMatch(UUID player1Id, UUID player2Id, GameSessionDTO gameSession) {
        messagingTemplate.convertAndSendToUser(player1Id.toString(), "/queue/match", gameSession);
        messagingTemplate.convertAndSendToUser(player2Id.toString(), "/queue/match", gameSession);
    }

    private void notifyOpponentOfAnswer(AnswerResultDTO answer) {
        UUID playerId = answer.getPlayerId();
        GameSession gameSession = activeGames.get(playerId);
        UUID opponentId = getOpponentId(playerId, gameSession);
        messagingTemplate.convertAndSendToUser(opponentId.toString(), "/queue/answer", answer);
    }

    public ResponseEntity<?> finishTheGame(GameSessionReq gameSessionReq) {
        GameSession gameSession = gameSessionRepo.findById(gameSessionReq.getGameSessionId()).orElseThrow();
        gameSession.setStatus("FINISHED");
        gameSessionRepo.save(gameSession);
        return ResponseEntity.ok(gameSession);
    }

    public ResponseEntity<?> getMatchHistories() {
        User user = userService.getCurrentUser();
        var histories = gameSessionService.getUserHistory(user.getId());
        return ResponseEntity.ok(histories);
    }


}

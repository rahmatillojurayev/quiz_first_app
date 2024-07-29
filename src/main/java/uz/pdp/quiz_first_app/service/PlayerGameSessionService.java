package uz.pdp.quiz_first_app.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.entity.PlayerGameSession;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.repo.PlayerGameSessionRepo;
import uz.pdp.quiz_first_app.repo.UserRepo;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerGameSessionService {

    private final UserRepo userRepo;
    private final PlayerGameSessionRepo playerGameSessionRepo;

    public PlayerGameSession createPlayerGameSession(User player) {
        var playerGameSession = PlayerGameSession.builder()
                .user(player)
                .wrongAnswers(10)
                .correctAnswers(0)
                .score(0)
                .build();
        return playerGameSessionRepo.save(playerGameSession);
    }

    @Async
    public void updatePlayerResults(UUID playerId) {
        var playerGameSession = playerGameSessionRepo.findByUserId(playerId);
        playerGameSession.setCorrectAnswers(playerGameSession.getCorrectAnswers() + 1);
        playerGameSession.setWrongAnswers(playerGameSession.getWrongAnswers() - 1);
        playerGameSession.setScore(playerGameSession.getScore() + 20);
        playerGameSessionRepo.save(playerGameSession);
    }

    @Transactional
    public void updatePlayerScore(UUID player1GameSessionId, UUID player2GameSessionId) {
        PlayerGameSession player1GameSession = playerGameSessionRepo.findById(player1GameSessionId)
                .orElseThrow(() -> new RuntimeException("Player 1 Game Session not found: " + player1GameSessionId));
        PlayerGameSession player2GameSession = playerGameSessionRepo.findById(player2GameSessionId)
                .orElseThrow(() -> new RuntimeException("Player 2 Game Session not found: " + player2GameSessionId));
        if (player1GameSession.getScore() > player2GameSession.getScore()) {
            player1GameSession.setScore(player1GameSession.getScore() + 20);
        } else if (player1GameSession.getScore() < player2GameSession.getScore()) {
            player2GameSession.setScore(player2GameSession.getScore() + 20);
        } else {
            player1GameSession.setScore(player1GameSession.getScore() + 10);
            player2GameSession.setScore(player2GameSession.getScore() + 10);
        }
        playerGameSessionRepo.save(player1GameSession);
        playerGameSessionRepo.save(player2GameSession);
    }

    @Transactional
    public void updatePlayersStatus(UUID player1GameSessionId, UUID player2GameSessionId) {
        userRepo.updateUserStatusByPlayerId(player1GameSessionId, "ONLINE");
        userRepo.updateUserStatusByPlayerId(player2GameSessionId, "ONLINE");
    }

}

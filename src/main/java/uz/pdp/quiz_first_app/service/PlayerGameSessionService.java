package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.entity.PlayerGameSession;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.repo.PlayerGameSessionRepo;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerGameSessionService {

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

}

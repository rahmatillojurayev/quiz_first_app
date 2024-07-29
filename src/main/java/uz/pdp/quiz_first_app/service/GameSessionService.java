package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.projection.*;
import uz.pdp.quiz_first_app.repo.GameSessionRepo;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameSessionService {

    private final GameSessionRepo gameSessionRepo;

    public List<HistoryProjection> getUserHistory(UUID userId) {
        List<RawGameSessionData> rawData = gameSessionRepo.getRawUserHistory(userId);
        return rawData.stream().map(data -> new HistoryProjection() {
            public String getYou() {
                return data.getPlayer1Id().equals(userId) ? data.getPlayer1Username() : data.getPlayer2Username();
            }
            public String getOpponent() {
                return data.getPlayer1Id().equals(userId) ? data.getPlayer2Username() : data.getPlayer1Username();
            }
            public Integer getYourScore() {
                return data.getPlayer1Id().equals(userId) ? data.getPlayer1Score() : data.getPlayer2Score();
            }
            public Integer getOpponentScore() {
                return data.getPlayer1Id().equals(userId) ? data.getPlayer2Score() : data.getPlayer1Score();
            }
            public Boolean getWin() {
                if (data.getPlayer1Id().equals(userId)) {
                    return data.getPlayer1Score() > data.getPlayer2Score();
                } else {
                    return data.getPlayer2Score() > data.getPlayer1Score();
                }
            }
        }).collect(Collectors.toList());
    }

}

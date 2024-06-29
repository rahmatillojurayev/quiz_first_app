package uz.pdp.quiz_first_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.entity.MultiPlayer;
import uz.pdp.quiz_first_app.repo.MultiPlayerRepository;

import java.util.Optional;

@Service
public class MultiPlayerService {

    @Autowired
    private MultiPlayerRepository multiPlayerRepository;

    public MultiPlayer createGame(MultiPlayer multiPlayer) {
        return multiPlayerRepository.save(multiPlayer);
    }

    public Optional<MultiPlayer> getGameById(Integer id) {
        return multiPlayerRepository.findById(id);
    }

    public MultiPlayer updateGame(MultiPlayer multiPlayer) {
        return multiPlayerRepository.save(multiPlayer);
    }

    public void deleteGame(Integer id) {
        multiPlayerRepository.deleteById(id);
    }

    public void determineWinner(MultiPlayer multiPlayer) {
        if (multiPlayer.getScore() > multiPlayer.getOpponent().getScore()) {
            // Owner wins
        } else if (multiPlayer.getScore() < multiPlayer.getOpponent().getScore()) {
            // Opponent wins
        } else {
            // Draw
        }
    }
}

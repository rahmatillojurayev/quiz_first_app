package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.entity.MultiPlayer;
import uz.pdp.quiz_first_app.repo.MultiPlayerRepository;
import uz.pdp.quiz_first_app.repo.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MultiPlayerService {

    @Autowired
    private MultiPlayerRepository multiPlayerRepository;
    private final UserRepo userRepo;

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
        if (multiPlayer.getOwner().getScore() > multiPlayer.getOpponent().getScore()) {

        if (multiPlayer.getOwner().getScore() == multiPlayer.getOpponent().getScore()) {
        }

        } else if (multiPlayer.getOwner().getScore() < multiPlayer.getOpponent().getScore()) {

        } else  {

        }
    }

    public List<MultiPlayer> getAllGames() {
        return multiPlayerRepository.findAll();
    }
}

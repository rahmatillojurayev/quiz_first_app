package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.SingleGameDTO;
import uz.pdp.quiz_first_app.entity.SingleGame;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.repo.SingleGameRepo;
import uz.pdp.quiz_first_app.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class SingleGameService {

    private final UserRepo userRepo;
    private final SingleGameRepo singleGameRepo;

    public ResponseEntity<?> createAndSave(SingleGameDTO singleGameDTO) {
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.findByEmail(principal).orElseThrow();
        SingleGame singleGame = SingleGame.builder()
                .user(user)
                .score(singleGameDTO.getScore())
                .correctAnswers(singleGameDTO.getCorrectAnswers())
                .wrongAnswers(singleGameDTO.getWrongAnswers())
                .build();
        singleGameRepo.save(singleGame);
        return ResponseEntity.ok(singleGame);
    }

}

package uz.pdp.quiz_first_app.controller.game;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.quiz_first_app.dto.game.GameSessionReq;
import uz.pdp.quiz_first_app.service.MultiplayerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/multiplayer")
public class MultiplayerController {

    private final MultiplayerService multiplayerService;

    @PostMapping("/finish")
    public ResponseEntity<?> finishGame(@RequestBody GameSessionReq gameSessionReq){
        return multiplayerService.finishTheGame(gameSessionReq);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory(){
        return multiplayerService.getMatchHistories();
    }

}

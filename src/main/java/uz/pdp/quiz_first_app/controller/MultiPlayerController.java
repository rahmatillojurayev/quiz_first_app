package uz.pdp.quiz_first_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import uz.pdp.quiz_first_app.entity.MultiPlayer;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.service.MultiPlayerService;
import uz.pdp.quiz_first_app.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/multiplayer")
@RequiredArgsConstructor
public class MultiPlayerController {

    private final MultiPlayerService multiPlayerService;
    private final UserService userService;


    @GetMapping("/start")
    public ResponseEntity<?> getAllGames() {
        userService.updateUser();
        User game = userService.getStartedUserTrue();
        return ResponseEntity.ok(game);
    }

    @PostMapping("/create")
    public ResponseEntity<MultiPlayer> createGame(@RequestBody MultiPlayer multiPlayer) {
        MultiPlayer createdGame = multiPlayerService.createGame(multiPlayer);
        return ResponseEntity.ok(createdGame);
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<MultiPlayer> getGame(@PathVariable Integer id) {
        Optional<MultiPlayer> game = multiPlayerService.getGameById(id);
        return game.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<MultiPlayer> updateGame(@RequestBody MultiPlayer multiPlayer) {
        MultiPlayer updatedGame = multiPlayerService.updateGame(multiPlayer);
        return ResponseEntity.ok(updatedGame);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Integer id) {
        multiPlayerService.deleteGame(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/determine-winner")
    public ResponseEntity<Void> determineWinner(@RequestBody MultiPlayer multiPlayer) {
        multiPlayerService.determineWinner(multiPlayer);
        return ResponseEntity.ok().build();
    }
}

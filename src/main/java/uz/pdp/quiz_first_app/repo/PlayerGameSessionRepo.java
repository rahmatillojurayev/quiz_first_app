package uz.pdp.quiz_first_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.quiz_first_app.entity.PlayerGameSession;
import java.util.UUID;

public interface PlayerGameSessionRepo extends JpaRepository<PlayerGameSession, UUID> {

    PlayerGameSession findByUserId(UUID userId);

}
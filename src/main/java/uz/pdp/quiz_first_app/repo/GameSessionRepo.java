package uz.pdp.quiz_first_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.quiz_first_app.entity.GameSession;
import uz.pdp.quiz_first_app.projection.RawGameSessionData;
import java.util.List;
import java.util.UUID;

public interface GameSessionRepo extends JpaRepository<GameSession, Integer> {

    @Query(value = """
                    select
                        u1.username as player1Username,
                        u2.username as player2Username,
                        pgs1.score as player1Score,
                        pgs2.score as player2Score,
                        pgs1.user_id as player1Id,
                        pgs2.user_id as player2Id
                    from game_session gs
                    join player_game_session pgs1 on gs.player1game_session_id = pgs1.id
                    join player_game_session pgs2 on gs.player2game_session_id = pgs2.id
                    join users u1 on pgs1.user_id = u1.id
                    join users u2 on pgs2.user_id = u2.id
                    where pgs1.user_id = :id or pgs2.user_id = :id and gs.status = 'FINISHED'
                    """, nativeQuery = true)
    List<RawGameSessionData> getRawUserHistory(UUID id);

}

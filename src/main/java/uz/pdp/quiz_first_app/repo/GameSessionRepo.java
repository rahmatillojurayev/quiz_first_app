package uz.pdp.quiz_first_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.quiz_first_app.entity.GameSession;
import uz.pdp.quiz_first_app.projection.PlayerScoreProjection;
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

    @Query(value = """
                select
                    pgs.user_id as playerId,
                    u.username as playerName,
                    sum(pgs.score) as score,
                    rank() over (order by sum(pgs.score) desc) as ranking
                from player_game_session pgs
                join users u on pgs.user_id = u.id
                join game_session gs on pgs.id in (gs.player1game_session_id, gs.player2game_session_id)
                where gs.status = 'FINISHED'
                group by pgs.user_id, u.username
                order by score desc
                limit 10
                """, nativeQuery = true)
    List<PlayerScoreProjection> getLeaderBoard();



}

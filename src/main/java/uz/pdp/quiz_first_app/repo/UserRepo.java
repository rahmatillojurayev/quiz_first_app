package uz.pdp.quiz_first_app.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.quiz_first_app.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String username);

    boolean existsByEmail(String username);

    @Query(value = """
                    select u.* from users u
                    where u.id <> :userId and u.user_status ilike 'SEARCHING'
                    """, nativeQuery = true)
    List<User> findSearchingUsers(UUID userId);

    @Modifying
    @Transactional
    @Query(value = """
        update users u
        set user_status = :status
        where u.id = (
            select pgs.user_id from player_game_session pgs where pgs.id = :playerId
        )
    """, nativeQuery = true)
    void updateUserStatusByPlayerId(UUID playerId, String status);

}
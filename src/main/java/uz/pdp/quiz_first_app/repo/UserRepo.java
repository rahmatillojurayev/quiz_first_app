package uz.pdp.quiz_first_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
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
                    join user_status us on us.id = u.user_status_id
                    where u.id <> :userId and us.status ilike 'searching'
                    """, nativeQuery = true)
    List<User> findSearchingUsers(UUID userId);

}
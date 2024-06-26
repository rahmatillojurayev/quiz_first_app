package uz.pdp.quiz_first_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.quiz_first_app.entity.Role;
import uz.pdp.quiz_first_app.entity.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(RoleName roleName);

}
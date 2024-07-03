package uz.pdp.quiz_first_app.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.quiz_first_app.entity.Role;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.entity.enums.RoleName;
import uz.pdp.quiz_first_app.repo.RoleRepo;
import uz.pdp.quiz_first_app.repo.UserRepo;
import uz.pdp.quiz_first_app.util.DataInitializationService;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;
    private final DataInitializationService dataInitializationService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) {
        if (ddl.equals("create")) {
            generateUsers();
            dataInitializationService.initializeData();
        }
    }

    private void generateUsers() {
        Role role = Role.builder()
                .id(1)
                .roleName(RoleName.ROLE_ADMIN)
                .build();
        Role role1 = Role.builder()
                .id(2)
                .roleName(RoleName.ROLE_USER)
                .build();
        roleRepo.save(role);
        roleRepo.save(role1);
        User user1 = User.builder()
                .email("brave")
                .password(passwordEncoder.encode("1"))
                .roles(List.of(role))
                .build();

        User user2 = User.builder()
                .email("clint")
                .password(passwordEncoder.encode("1"))
                .roles(List.of(role1))
                .build();

        User user3 = User.builder()
                .email("roger")
                .password(passwordEncoder.encode("1"))
                .roles(List.of(role1))
                .build();

        User user4 = User.builder()
                .email("raven")
                .password(passwordEncoder.encode("1"))
                .roles(List.of(role1))
                .build();

        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);
    }

}

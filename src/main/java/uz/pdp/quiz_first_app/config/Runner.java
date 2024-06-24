package uz.pdp.quiz_first_app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.quiz_first_app.entity.Role;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.entity.enums.RoleName;
import uz.pdp.quiz_first_app.repo.RoleRepository;
import uz.pdp.quiz_first_app.repo.UserRepository;
import uz.pdp.quiz_first_app.security.JwtUtil;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {


    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) throws Exception {
        if (ddl.equals("create")) {
            generateUsers();
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
        roleRepository.save(role);
        roleRepository.save(role1);
        User user = User.builder()
                .email("admin")
                .password(passwordEncoder.encode("123"))
                .roles(List.of(role))
                .build();
        userRepository.save(user);

        User user1 = User.builder()
                .email("user")
                .password(passwordEncoder.encode("123"))
                .roles(List.of(role1))
                .build();
        userRepository.save(user1);
    }
}

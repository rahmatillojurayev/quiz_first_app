
package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.RegisterDTO;
import uz.pdp.quiz_first_app.entity.Role;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.entity.enums.RoleName;
import uz.pdp.quiz_first_app.repo.RoleRepository;
import uz.pdp.quiz_first_app.repo.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    public boolean register(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            return false;
        }
        Role role = roleRepository.findByRoleName(RoleName.ROLE_USER);
        if (role != null) {
            User user = new User();
            user.setEmail(registerDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            user.setConfirmPassword(passwordEncoder.encode(registerDTO.getConfirmPassword()));
            user.setRoles(List.of(role));
            user.setActive(false);
            emailService.sendEmailConfirmCode(user);
        }
        return true;
    }

    public boolean confirm(RegisterDTO registerDTO) {
        Optional<User> user = userRepository.findByEmail(registerDTO.getEmail());
        if (user.isPresent()) {
            User user1 = user.get();
            if (Objects.equals(user1.getActivationCode(), registerDTO.getActivationCode())) {
                user1.setActive(true);
                userRepository.save(user1);
                return true;
            }
        }
        return false;
    }
}

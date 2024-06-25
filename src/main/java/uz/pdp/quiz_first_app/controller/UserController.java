package uz.pdp.quiz_first_app.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.repo.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("home")
    public String home() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user =  userRepository.findByEmail(email);
        if (user.get().getActive()) {
            return "Welcome";
        } else {
            User user1 = user.get();
            userRepository.delete(user1);
            SecurityContextHolder.clearContext();
            SecurityContextHolder.getContext().setAuthentication(null);
            return "Your account is not registered";
        }
    }


    @GetMapping("/me")
    public Optional<User> getUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(email);
    }

}

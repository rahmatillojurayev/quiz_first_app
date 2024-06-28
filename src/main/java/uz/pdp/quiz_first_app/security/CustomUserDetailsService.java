package uz.pdp.quiz_first_app.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.entity.User;
import uz.pdp.quiz_first_app.repo.UserRepo;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> customUser = userRepo.findByEmail(username);
        if (customUser.isPresent()) {
            return customUser.get();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

}

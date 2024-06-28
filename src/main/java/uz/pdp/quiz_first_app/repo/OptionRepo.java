package uz.pdp.quiz_first_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.quiz_first_app.entity.Option;

public interface OptionRepo extends JpaRepository<Option, Integer> {
}

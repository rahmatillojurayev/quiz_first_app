package uz.pdp.quiz_first_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.quiz_first_app.entity.Option;
import java.util.List;

public interface OptionRepo extends JpaRepository<Option, Integer> {

    List<Option> findAllByQuestionId(Integer questionId);

}

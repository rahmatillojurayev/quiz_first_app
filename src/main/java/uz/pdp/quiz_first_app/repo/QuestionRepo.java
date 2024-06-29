package uz.pdp.quiz_first_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.quiz_first_app.entity.Question;
import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Integer> {

    @Query(value = "SELECT * FROM question q where q.category_id = :categoryId ORDER BY RANDOM() limit 10",
            nativeQuery = true)
    List<Question> findRandomQuestionsByCategoryId(Integer categoryId);

}

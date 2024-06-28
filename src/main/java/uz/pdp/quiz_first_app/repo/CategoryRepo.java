package uz.pdp.quiz_first_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.quiz_first_app.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}

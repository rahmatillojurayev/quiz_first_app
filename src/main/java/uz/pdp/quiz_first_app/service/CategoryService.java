package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.entity.Category;
import uz.pdp.quiz_first_app.repo.CategoryRepo;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public Category getCategory(Integer categoryId) {
        if (categoryId == null) return null;
        return categoryRepo.findById(categoryId).orElse(null);
    }

}

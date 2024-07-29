package uz.pdp.quiz_first_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.entity.Category;
import uz.pdp.quiz_first_app.repo.CategoryRepo;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public ResponseEntity<?> getCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElse(null);
        if (category == null) return ResponseEntity.status(404).body("Category not found");
        return ResponseEntity.ok(category);
    }

    public ResponseEntity<?> addCategory(Category category) {
        if (categoryRepo.existsByName(category.getName())) return ResponseEntity.status(409).body("Category already exists");
        categoryRepo.save(category);
        return ResponseEntity.ok("Category added");
    }

    public ResponseEntity<?> getCategories() {
        if (categoryRepo.findAll().isEmpty()) return ResponseEntity.status(404).body("Category not found");
        return ResponseEntity.ok(categoryRepo.findAll());
    }

    public ResponseEntity<?> deleteCategory(Integer categoryId) {
        if (categoryId == null) return ResponseEntity.status(404).body("Category not found");
        categoryRepo.deleteById(categoryId);
        return ResponseEntity.ok("Category deleted");
    }

    public ResponseEntity<?> editCategory(Integer categoryId, Category category) {
        Category foundCategory = categoryRepo.findById(categoryId).orElse(null);
        if (foundCategory == null) return ResponseEntity.status(404).body("Category not found");
        foundCategory.setName(category.getName());
        categoryRepo.save(foundCategory);
        return ResponseEntity.ok("Category edited");
    }

}

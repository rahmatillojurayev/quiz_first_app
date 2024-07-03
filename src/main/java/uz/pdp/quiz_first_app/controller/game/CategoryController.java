package uz.pdp.quiz_first_app.controller.game;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.quiz_first_app.entity.Category;
import uz.pdp.quiz_first_app.repo.CategoryRepo;
import uz.pdp.quiz_first_app.service.CategoryService;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepo categoryRepo;
    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        if (categoryRepo.existsByName(category.getName())) return ResponseEntity.status(409).body("Category already exists");
        categoryRepo.save(category);
        return ResponseEntity.ok("Category added");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getCategories() {
        if (categoryRepo.findAll().isEmpty()) return ResponseEntity.status(404).body("Category not found");
        return ResponseEntity.ok(categoryRepo.findAll());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable Integer categoryId) {
        Category category = categoryService.getCategory(categoryId);
        if (category == null) return ResponseEntity.status(404).body("Category not found");
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) {
        Category category = categoryService.getCategory(categoryId);
        if (category == null) return ResponseEntity.status(404).body("Category not found");
        categoryRepo.deleteById(categoryId);
        return ResponseEntity.ok("Category deleted");
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> editCategory(@PathVariable Integer categoryId, @RequestBody Category category) {
        Category category1 = categoryService.getCategory(categoryId);
        if (category1 == null) return ResponseEntity.status(404).body("Category not found");
        category1.setName(category.getName());
        categoryRepo.save(category1);
        return ResponseEntity.ok("Category edited");
    }
}

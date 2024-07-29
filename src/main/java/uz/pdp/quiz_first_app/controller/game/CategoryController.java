package uz.pdp.quiz_first_app.controller.game;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.quiz_first_app.entity.Category;
import uz.pdp.quiz_first_app.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable Integer categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> editCategory(@PathVariable Integer categoryId, @RequestBody Category category) {
        return categoryService.editCategory(categoryId, category);
    }

}

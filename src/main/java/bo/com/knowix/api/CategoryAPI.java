package bo.com.knowix.api;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.com.knowix.bl.CategoryBL;
import bo.com.knowix.entity.CategoryEntity;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryAPI {
    
    private final CategoryBL categoryBL;
    private static final Logger LOGGER = Logger.getLogger(CategoryAPI.class.getName());

    @Autowired
    public CategoryAPI(CategoryBL categoryBL) {
        this.categoryBL = categoryBL;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        LOGGER.info("Starting process to fetch all categories");
        try {
            List<CategoryEntity> categories = categoryBL.findAllCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            LOGGER.warning("Error occurred while fetching categories: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to fetch all categories");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable("id") int categoryId) {
        LOGGER.info("Starting process to fetch category by ID: " + categoryId);
        try {
            Optional<CategoryEntity> category = categoryBL.findCategoryById(categoryId);
            if (category.isPresent()) {
                return ResponseEntity.ok(category.get());
            } else {
                LOGGER.info("Category not found with ID: " + categoryId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            LOGGER.warning("Error occurred while fetching category by ID: " + categoryId + " - " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to fetch category by ID: " + categoryId);
        }
    }
}

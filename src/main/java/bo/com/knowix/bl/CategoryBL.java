package bo.com.knowix.bl;

import bo.com.knowix.dao.CategoryDAO;
import bo.com.knowix.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryBL {

    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryBL(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<CategoryEntity> findAllCategories() {
        return categoryDAO.findAll();
    }

    public Optional<CategoryEntity> findCategoryById(int categoryId) {
        return categoryDAO.findById(categoryId);
    }
}

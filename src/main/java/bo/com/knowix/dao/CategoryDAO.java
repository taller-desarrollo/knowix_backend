package bo.com.knowix.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.com.knowix.entity.CategoryEntity;

public interface CategoryDAO extends JpaRepository<CategoryEntity, Integer> {

}

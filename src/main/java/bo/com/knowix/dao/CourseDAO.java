package bo.com.knowix.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.com.knowix.entity.CourseEntity;
import java.util.List;

public interface CourseDAO extends JpaRepository<CourseEntity, Integer> {

    List<CourseEntity> findByKcUserKcUuid(String kcUserKcUuid);
    
}

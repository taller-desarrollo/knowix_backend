package bo.com.knowix.dao;

import bo.com.knowix.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseDAO extends JpaRepository<CourseEntity, Integer> {

    List<CourseEntity> findByKcUserKcUuid(String kcUserKcUuid);
    
    Page<CourseEntity> findByCourseNameIgnoreCaseContaining(String courseName, Pageable pageable);
}

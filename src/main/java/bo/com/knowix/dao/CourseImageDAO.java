package bo.com.knowix.dao;

import bo.com.knowix.entity.CourseImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseImageDAO extends JpaRepository<CourseImageEntity, Integer> {
    List<CourseImageEntity> findByCourseCourseId(Integer courseCourseId);
}

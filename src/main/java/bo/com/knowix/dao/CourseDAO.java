package bo.com.knowix.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.com.knowix.entity.CourseEntity;

public interface CourseDAO extends JpaRepository<CourseEntity, Integer> {

}

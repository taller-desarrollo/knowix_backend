package bo.com.knowix.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.com.knowix.entity.SectionEntity;

public interface SectionDAO extends JpaRepository<SectionEntity, Integer> {
    // List<SectionEntity> findByCourseId(Integer courseId);
}

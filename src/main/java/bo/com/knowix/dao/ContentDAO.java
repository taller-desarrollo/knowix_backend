package bo.com.knowix.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.com.knowix.entity.ContentEntity;

public interface ContentDAO extends JpaRepository<ContentEntity, Integer> {
    List<ContentEntity> findBySectionId(Integer sectionId);
}

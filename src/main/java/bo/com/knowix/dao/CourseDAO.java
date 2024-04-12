package bo.com.knowix.dao;

import bo.com.knowix.entity.CategoryEntity;
import bo.com.knowix.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseDAO extends JpaRepository<CourseEntity, Integer> {

    Page<CourseEntity> findByKcUserKcUuid(String kcUserKcUuid, Pageable pageable);

    Page<CourseEntity> findAllByCourseIsPublicIsTrueAndStatusEquals( String status, Pageable pageable);
    
    Page<CourseEntity> findByCourseNameIgnoreCaseContaining(String courseName,Pageable pageable);

    Page<CourseEntity> findByCourseNameContainingIgnoreCaseAndCourseIsPublicIsTrueAndStatusEquals(String searchTerm, String status, Pageable pageable);

    Page<CourseEntity> findByCourseNameContainingIgnoreCaseAndCourseStandardPriceBetweenAndCourseIsPublicIsTrueAndStatusEquals(String searchTerm, Double minPrice, Double maxPrice, String status, Pageable pageable);

    Page<CourseEntity> findByCourseStandardPriceBetweenAndCourseIsPublicIsTrueAndStatusEquals(Double minPrice, Double maxPrice, String status, Pageable pageable);

    Page<CourseEntity> findByCourseNameContainingIgnoreCaseAndCourseStandardPriceBetweenAndCategoryInAndCourseIsPublicIsTrueAndStatusEquals(
            String searchTerm,
            Double minPrice,
            Double maxPrice,
            List<CategoryEntity> categories,
            String status,
            Pageable pageable
    );

    Page<CourseEntity> findByCourseNameContainingIgnoreCaseAndCategoryInAndCourseIsPublicIsTrueAndStatusEquals(
            String searchTerm,
            List<CategoryEntity> categories,
            String status,
            Pageable pageable
    );

    Page<CourseEntity> findByCourseStandardPriceBetweenAndCategoryInAndCourseIsPublicIsTrueAndStatusEquals(
            Double minPrice,
            Double maxPrice,
            List<CategoryEntity> categories,
            String status,
            Pageable pageable
    );

    Page<CourseEntity> findByCategoryInAndCourseIsPublicIsTrueAndStatusEquals(
            List<CategoryEntity> categories,
            String status,
            Pageable pageable
    );
}

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

    List<CourseEntity> findByKcUserKcUuid(String kcUserKcUuid);
    
    Page<CourseEntity> findByCourseNameIgnoreCaseContaining(String courseName, Pageable pageable);

    Page<CourseEntity> findByCourseNameContainingIgnoreCase(String searchTerm, Pageable pageable);

    Page<CourseEntity> findByCourseNameContainingIgnoreCaseAndCourseStandardPriceBetween(String searchTerm, Double minPrice, Double maxPrice, Pageable pageable);

    @Query("SELECT c FROM CourseEntity c WHERE LOWER(c.courseName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) AND c.courseStandardPrice BETWEEN :minPrice AND :maxPrice")
    Page<CourseEntity> findCoursesBySearchTermAndPriceRange(
            @Param("searchTerm") String searchTerm,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable
    );

    Page<CourseEntity> findByCourseStandardPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);

    Page<CourseEntity> findByCourseNameContainingIgnoreCaseAndCourseStandardPriceBetweenAndCategory(
            String searchTerm,
            Double minPrice,
            Double maxPrice,
            CategoryEntity category,
            Pageable pageable
    );

    Page<CourseEntity> findByCourseNameContainingIgnoreCaseAndCategory(
            String searchTerm,
            CategoryEntity category,
            Pageable pageable
    );

    Page<CourseEntity> findByCourseStandardPriceBetweenAndCategory(
            Double minPrice,
            Double maxPrice,
            CategoryEntity category,
            Pageable pageable
    );

    Page<CourseEntity> findByCategory(
            CategoryEntity category,
            Pageable pageable
    );
}

package bo.com.knowix.bl;

import bo.com.knowix.dao.CategoryDAO;
import bo.com.knowix.dao.CourseDAO;
import bo.com.knowix.dao.LanguageDAO;
import bo.com.knowix.dto.CourseDTO;
import bo.com.knowix.entity.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseBL {

    private final CourseDAO courseDAO;
    private final CategoryDAO categoryDAO;
    private final LanguageDAO languageDAO;

    @Autowired
    public CourseBL(CourseDAO courseDAO, CategoryDAO categoryDAO, LanguageDAO languageDAO) {
        this.courseDAO = courseDAO;
        this.categoryDAO = categoryDAO;
        this.languageDAO = languageDAO;
    }

    public CourseEntity createCourse(CourseDTO courseDTO, String kcUserKcUuid) {
        CourseEntity course = new CourseEntity();
        course.setCourseDescription(courseDTO.getCourseDescription());
        course.setCourseStandardPrice(courseDTO.getCourseStandardPrice());
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseRequirements(courseDTO.getCourseRequirements());
        course.setStatus(courseDTO.getStatus()); // Asumiendo que quieres usar el status del DTO
        course.setKcUserKcUuid(kcUserKcUuid); // Establecer el UUID del usuario
        
        // Buscar categoría e idioma por ID y asignarlos al curso
        categoryDAO.findById(courseDTO.getCategoryCategoryId()).ifPresent(course::setCategory);
        languageDAO.findById(courseDTO.getLanguageLanguageId()).ifPresent(course::setLanguage);

        return courseDAO.save(course);
    }

    public List<CourseEntity> findAllCourses() {
        return courseDAO.findAll();
    }

    public Optional<CourseEntity> findCourseById(Integer courseId) {
        return courseDAO.findById(courseId);
    }

    public CourseEntity updateCourse(Integer courseId, CourseDTO courseDTO, String kcUserKcUuid) {
        return courseDAO.findById(courseId).map(course -> {
            course.setCourseDescription(courseDTO.getCourseDescription());
            course.setCourseStandardPrice(courseDTO.getCourseStandardPrice());
            course.setCourseName(courseDTO.getCourseName());
            course.setCourseRequirements(courseDTO.getCourseRequirements());
            course.setStatus(courseDTO.getStatus());
            course.setKcUserKcUuid(kcUserKcUuid); // Actualizar el UUID del usuario
            
            // Buscar categoría e idioma por ID y asignarlos al curso si es necesario
            categoryDAO.findById(courseDTO.getCategoryCategoryId()).ifPresent(course::setCategory);
            languageDAO.findById(courseDTO.getLanguageLanguageId()).ifPresent(course::setLanguage);
            
            return courseDAO.save(course);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }
}

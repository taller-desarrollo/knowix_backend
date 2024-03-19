package bo.com.knowix.api;

import bo.com.knowix.bl.CourseBL;
import bo.com.knowix.dto.CourseDTO;
import bo.com.knowix.entity.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/course")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseAPI {

    private final CourseBL courseBL;
    private static final Logger LOGGER = Logger.getLogger(CourseAPI.class.getName());

    @Autowired
    public CourseAPI(CourseBL courseBL) {
        this.courseBL = courseBL;
    }

    @PostMapping()
    public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseDTO courseDTO) {
        LOGGER.info("Starting process to create a course");
        try {
            // Asumiendo que CourseDTO ahora incluye un campo para kcUserKcUuid
            CourseEntity newCourse = courseBL.createCourse(courseDTO, courseDTO.getKcUserKcUuid());
            return ResponseEntity.ok(newCourse);
        } catch (Exception e) {
            LOGGER.warning("Error occurred while creating a course: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to create a course");
        }
    }

    @GetMapping()
    public ResponseEntity<List<CourseEntity>> getAllCourses() {
        LOGGER.info("Starting process to fetch all courses");
        try {
            List<CourseEntity> courses = courseBL.findAllCourses();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            LOGGER.warning("Error occurred while fetching courses: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to fetch all courses");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEntity> getCourseById(@PathVariable("id") Integer courseId) {
        LOGGER.info("Starting process to fetch course by ID: " + courseId);
        try {
            return courseBL.findCourseById(courseId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            LOGGER.warning("Error occurred while fetching course by ID: " + courseId + " - " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to fetch course by ID: " + courseId);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable("id") Integer courseId, @RequestBody CourseDTO courseDTO) {
        LOGGER.info("Starting process to update course by ID: " + courseId);
        try {
            // Asumiendo que CourseDTO ahora incluye un campo para kcUserKcUuid
            CourseEntity updatedCourse = courseBL.updateCourse(courseId, courseDTO, courseDTO.getKcUserKcUuid());
            return ResponseEntity.ok(updatedCourse);
        } catch (Exception e) {
            LOGGER.warning("Error occurred while updating course by ID: " + courseId + " - " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to update course by ID: " + courseId);
        }
    }
}

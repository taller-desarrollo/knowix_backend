package bo.com.knowix.api;

import bo.com.knowix.bl.CourseBL;
import bo.com.knowix.dto.CourseDTO;
import bo.com.knowix.dto.CourseDTOResponse;
import bo.com.knowix.dto.SectionDTO;
import bo.com.knowix.dto.Requests.CreateContentRequestDTO;
import bo.com.knowix.entity.ContentEntity;
import bo.com.knowix.entity.CourseEntity;
import bo.com.knowix.entity.SectionEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;

import org.springframework.data.domain.Page;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/course")
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
    public ResponseEntity<Page<CourseEntity>> getAllCourses(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) List<Integer> categoryIds,
            @RequestParam(defaultValue = "asc") String sort) {
        LOGGER.info("Starting process to fetch all courses");
        try {
            Pageable pageable;
            if (sort.equals("desc")) {
                pageable = PageRequest.of(page, size, Sort.by("courseStandardPrice").descending());
            } else {
                pageable = PageRequest.of(page, size, Sort.by("courseStandardPrice").ascending());
            }

            Page<CourseEntity> coursesPage;
            if (categoryIds != null && !categoryIds.isEmpty()) {
                if (searchTerm != null && !searchTerm.isEmpty()) {
                    if (minPrice != null && maxPrice != null) {
                        coursesPage = courseBL.findCoursesBySearchTermAndPriceRangeAndCategoryId(searchTerm, minPrice,
                                maxPrice, categoryIds, pageable);
                    } else {
                        coursesPage = courseBL.findCoursesBySearchTermAndCategoryId(searchTerm, categoryIds, pageable);
                    }
                } else {
                    if (minPrice != null && maxPrice != null) {
                        coursesPage = courseBL.findCoursesByPriceRangeAndCategoryId(minPrice, maxPrice, categoryIds,
                                pageable);
                    } else {
                        coursesPage = courseBL.findCoursesByCategoryId(categoryIds, pageable);
                    }
                }
            } else if (searchTerm != null && !searchTerm.isEmpty()) {
                if (minPrice != null && maxPrice != null) {
                    coursesPage = courseBL.findCoursesBySearchTermAndPriceRange(searchTerm, minPrice, maxPrice,
                            pageable);
                } else {
                    coursesPage = courseBL.findCoursesBySearchTerm(searchTerm, pageable);
                }
            } else {
                if (minPrice != null && maxPrice != null) {
                    coursesPage = courseBL.findCoursesByPriceRange(minPrice, maxPrice, pageable);
                } else {
                    coursesPage = courseBL.findAllCourses(pageable);
                }
            }
            Page<CourseEntity> courses = coursesPage;
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
    public ResponseEntity<?> updateCourse(@PathVariable("id") Integer courseId, @RequestBody CourseDTO courseDTO) {
        LOGGER.info("Starting process to update course by ID: " + courseId);
        try {
            // Aquí se asume que la lógica de negocio en BL podría lanzar una excepción
            // RuntimeException
            // para casos específicos, como no encontrar el curso o el usuario no
            // autorizado.
            CourseEntity updatedCourse = courseBL.updateCourse(courseId, courseDTO, courseDTO.getKcUserKcUuid());
            return ResponseEntity.ok(updatedCourse);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Curso no encontrado")) {
                LOGGER.warning("Course not found: " + e.getMessage());
                return ResponseEntity.notFound().build();
            } else if (e.getMessage().contains("El usuario no está autorizado")) {
                LOGGER.warning("Unauthorized user attempt to update course: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized: " + e.getMessage());
            } else {
                LOGGER.warning("Error occurred while updating course by ID: " + courseId + " - " + e.getMessage());
                // Aquí decidimos devolver un error genérico de servidor.
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("An unexpected error occurred: " + e.getMessage());
            }
        } finally {
            LOGGER.info("Finished process to update course by ID: " + courseId);
        }
    }

    @PutMapping("/{id}/is-public")
    public ResponseEntity<CourseEntity> updateCourseIsPublic(@PathVariable("id") Integer courseId,
            @RequestHeader("X-UUID") String kcuuid) {
        LOGGER.info("Starting process to update course is public by ID: " + courseId);
        try {
            CourseEntity updatedCourse = courseBL.updateCourseIsPublic(courseId, kcuuid);
            return ResponseEntity.ok(updatedCourse);
        } catch (Exception e) {
            LOGGER.warning(
                    "Error occurred while updating course is public by ID: " + courseId + " - " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to update course is public by ID: " + courseId);
        }
    }

    @PostMapping("/{id}/section")
    public ResponseEntity<SectionEntity> createSection(@PathVariable("id") Integer courseId,
            @RequestBody SectionDTO sectionDTO) {
        LOGGER.info("Starting process to create a section for course with ID: " + courseId);

        try {
            sectionDTO.setCourseId(courseId);
            SectionEntity newSection = courseBL.createSection(sectionDTO);
            // TODO fix recursion of data
            newSection.setCourse(null);

            return ResponseEntity.ok(newSection);
        } catch (Exception e) {
            LOGGER.warning("Error ocurred while creating a section: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to create section");
        }
    }

    // @GetMapping("/{id}/section")
    // public ResponseEntity<List<SectionEntity>> getSectionsByCourseId() {

    // }

    @PostMapping("/section/{id}/content")
    public ResponseEntity<ContentEntity> createContent(@PathVariable("id") Integer sectionId,
            @RequestBody CreateContentRequestDTO createContentRequestDTO) {
        // TODO unify loggers, use the other logger everywhere
        LOGGER.info("Starting process to create content for section with sectionId " + sectionId);

        try {
            createContentRequestDTO.sectionId = sectionId;
            ContentEntity newContent = courseBL.createContent(createContentRequestDTO);
            newContent.setSection(null);

            return ResponseEntity.ok(newContent);
        } catch (Exception e) {
            LOGGER.warning("Error ocurred while creating a content: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to create content");
        }
    }

    private CourseDTOResponse convertToDTO(CourseEntity courseEntity) {
        // Asume que ya tienes métodos o lógica para obtener los nombres de la categoría
        // y el idioma
        String categoryName = courseEntity.getCategory() != null ? courseEntity.getCategory().getCategoryName()
                : "Unknown Category";
        String languageName = courseEntity.getLanguage() != null ? courseEntity.getLanguage().getLanguageName()
                : "Unknown Language";

        return new CourseDTOResponse(
                courseEntity.getCourseId(),
                courseEntity.getCourseDescription(),
                courseEntity.getCourseStandardPrice(),
                courseEntity.getCourseName(),
                courseEntity.getCourseRequirements(),
                courseEntity.getStatus(),
                categoryName, // Nombre de la categoría
                languageName, // Nombre del idioma
                courseEntity.getKcUserKcUuid());
    }

    @GetMapping("/user/{kcUserKcUuid}")
    public ResponseEntity<Page<CourseEntity>> getCoursesByUserId(
            @PathVariable("kcUserKcUuid") String kcUserKcUuid,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "asc") String sort) {
        LOGGER.info("Starting process to fetch courses by user ID: " + kcUserKcUuid);
        try {
            Pageable pageable;
            if (sort.equals("desc")) {
                pageable = PageRequest.of(page, size, Sort.by("courseStandardPrice").descending());
            } else {
                pageable = PageRequest.of(page, size, Sort.by("courseStandardPrice").ascending());
            }

            Page<CourseEntity> courses = courseBL.findCoursesByUserId(kcUserKcUuid, pageable);
            return ResponseEntity.ok(courses);
            /*
             * List<CourseDTOResponse> courseDTOResponses = courses.stream()
             * .map(this::convertToDTO)
             * .collect(Collectors.toList());
             * 
             * if(courseDTOResponses.isEmpty()) {
             * LOGGER.info("No courses found for user ID: " + kcUserKcUuid);
             * return ResponseEntity.noContent().build();
             * }
             * LOGGER.info(courseDTOResponses.size() + " courses found for user ID: " +
             * kcUserKcUuid);
             * return ResponseEntity.ok(courseDTOResponses);
             */
        } catch (Exception e) {
            LOGGER.warning(
                    "Error occurred while fetching courses by user ID: " + kcUserKcUuid + " - " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to fetch courses by user ID: " + kcUserKcUuid);
        }
    }

    @PostMapping("/search")
    public Page<CourseEntity> searchCoursesByName(@RequestBody SearchCourseRequest searchCourseRequest) {
        return courseBL.findCoursesByName(searchCourseRequest.getName(), searchCourseRequest.getPage(),
                searchCourseRequest.getSize());
    }

    private static class SearchCourseRequest {
        private String name;
        private int page;
        private int size;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    @GetMapping("/{id}/creator")
    public ResponseEntity<String> getCourseCreator(@PathVariable("id") Integer courseId) {
        LOGGER.info("Starting process to fetch creator of course by ID: " + courseId);
        try {
            String userId = courseBL.findUserIdByCourseId(courseId);
            return ResponseEntity.ok(userId);
        } catch (Exception e) {
            LOGGER.warning(
                    "Error occurred while fetching creator of course by ID: " + courseId + " - " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to fetch creator of course by ID: " + courseId);
        }
    }
}

package bo.com.knowix.bl;

import bo.com.knowix.dao.AttachmentDAO;
import bo.com.knowix.dao.CategoryDAO;
import bo.com.knowix.dao.ContentDAO;
import bo.com.knowix.dao.CourseDAO;
import bo.com.knowix.dao.LanguageDAO;
import bo.com.knowix.dao.SectionDAO;
import bo.com.knowix.dto.AttachmentDTO;
import bo.com.knowix.dto.CourseDTO;
import bo.com.knowix.dto.SectionDTO;
import bo.com.knowix.dto.Requests.CreateContentRequestDTO;
import bo.com.knowix.entity.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import bo.com.knowix.dto.CourseDTOResponse;


@Service
public class CourseBL {

    private final CourseDAO courseDAO;
    private final CategoryDAO categoryDAO;
    private final LanguageDAO languageDAO;
    private final SectionDAO sectionDAO;
    private final ContentDAO contentDAO;
    private final AttachmentDAO attachmentDAO;

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CourseBL.class);

    @Autowired
    public CourseBL(CourseDAO courseDAO, CategoryDAO categoryDAO, LanguageDAO languageDAO, SectionDAO sectionDAO, ContentDAO contentDAO, AttachmentDAO attachmentDAO) {
        this.courseDAO = courseDAO;
        this.categoryDAO = categoryDAO;
        this.languageDAO = languageDAO;
        this.sectionDAO = sectionDAO;
        this.contentDAO = contentDAO;
        this.attachmentDAO = attachmentDAO;
    }


    public CourseEntity createCourse(CourseDTO courseDTO, String kcUserKcUuid) {
        CourseEntity course = new CourseEntity();
        course.setCourseDescription(courseDTO.getCourseDescription());
        course.setCourseStandardPrice(courseDTO.getCourseStandardPrice());
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseRequirements(courseDTO.getCourseRequirements());
        course.setStatus(courseDTO.getStatus()); // Asumiendo que quieres usar el status del DTO
        course.setKcUserKcUuid(kcUserKcUuid); // Establecer el UUID del usuario
        course.setCourseIsPublic(true);
        
        // Buscar categoría e idioma por ID y asignarlos al curso
        categoryDAO.findById(courseDTO.getCategoryCategoryId()).ifPresent(course::setCategory);
        languageDAO.findById(courseDTO.getLanguageLanguageId()).ifPresent(course::setLanguage);

        return courseDAO.save(course);
    }

    public Page<CourseEntity> findAllCourses(
            Pageable pageable
    ) {
        return courseDAO.findAllByCourseIsPublicIsTrueAndStatusEquals("true", pageable);
    }

    public Page<CourseEntity> findCoursesBySearchTerm(String searchTerm, Pageable pageable){
        return courseDAO.findByCourseNameContainingIgnoreCaseAndCourseIsPublicIsTrueAndStatusEquals(searchTerm,"true", pageable);
    }

    public Page<CourseEntity> findCoursesBySearchTermAndPriceRange(String searchTerm, Double minPrice, Double maxPrice, Pageable pageable){
        return courseDAO.findByCourseNameContainingIgnoreCaseAndCourseStandardPriceBetweenAndCourseIsPublicIsTrueAndStatusEquals(searchTerm, minPrice, maxPrice, "true", pageable);
    }

    public Page<CourseEntity> findCoursesByPriceRange(Double minPrice, Double maxPrice, Pageable pageable){
        return courseDAO.findByCourseStandardPriceBetweenAndCourseIsPublicIsTrueAndStatusEquals(minPrice, maxPrice, "true", pageable);
    }

    public Page<CourseEntity> findCoursesBySearchTermAndPriceRangeAndCategoryId(
            String searchTerm,
            Double minPrice,
            Double maxPrice,
            List<Integer> categoryIds,
            Pageable pageable
    ) {
        List<CategoryEntity> categories = categoryDAO.findAllById(categoryIds);
        return courseDAO.findByCourseNameContainingIgnoreCaseAndCourseStandardPriceBetweenAndCategoryInAndCourseIsPublicIsTrueAndStatusEquals(searchTerm, minPrice, maxPrice, categories, "true", pageable);
    }

    public Page<CourseEntity> findCoursesBySearchTermAndCategoryId(
            String searchTerm,
            List<Integer> categoryId,
            Pageable pageable
    ) {
        List<CategoryEntity> categories = categoryDAO.findAllById(categoryId);
        return courseDAO.findByCourseNameContainingIgnoreCaseAndCategoryInAndCourseIsPublicIsTrueAndStatusEquals(searchTerm, categories, "true", pageable);
    }

    public Page<CourseEntity> findCoursesByPriceRangeAndCategoryId(
            Double minPrice,
            Double maxPrice,
            List<Integer> categoryId,
            Pageable pageable
    ) {
        List<CategoryEntity> categories = categoryDAO.findAllById(categoryId);
        return courseDAO.findByCourseStandardPriceBetweenAndCategoryInAndCourseIsPublicIsTrueAndStatusEquals(minPrice, maxPrice, categories, "true", pageable);
    }

    public Page<CourseEntity> findCoursesByCategoryId(
            List<Integer> categoryId,
            Pageable pageable
    ) {
        List<CategoryEntity> categories = categoryDAO.findAllById(categoryId);
        return courseDAO.findByCategoryInAndCourseIsPublicIsTrueAndStatusEquals(categories, "true", pageable);
    }

    public Optional<CourseEntity> findCourseById(Integer courseId) {
        return courseDAO.findById(courseId);
    }

    public CourseEntity updateCourse(Integer courseId, CourseDTO courseDTO, String kcUserKcUuid) {
        CourseEntity existingCourse = courseDAO.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        if (!existingCourse.getKcUserKcUuid().equals(kcUserKcUuid)) {
            throw new RuntimeException("El usuario no está autorizado para editar este curso");
        }

        existingCourse.setCourseDescription(courseDTO.getCourseDescription());
        existingCourse.setCourseStandardPrice(courseDTO.getCourseStandardPrice());
        existingCourse.setCourseName(courseDTO.getCourseName());
        existingCourse.setCourseRequirements(courseDTO.getCourseRequirements());
        existingCourse.setStatus(courseDTO.getStatus());

        categoryDAO.findById(courseDTO.getCategoryCategoryId()).ifPresent(existingCourse::setCategory);
        languageDAO.findById(courseDTO.getLanguageLanguageId()).ifPresent(existingCourse::setLanguage);

        return courseDAO.save(existingCourse);
    }

    public CourseEntity updateCourseIsPublic(Integer courseId, String kcUuid) {
        CourseEntity existingCourse = courseDAO.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        //verificar que el curso modificado pertenezca al usuario que lo quiere eliminar
        if (!existingCourse.getKcUserKcUuid().equals(kcUuid)) {
            throw new RuntimeException("El usuario no está autorizado para editar este curso");
        }

        boolean isPublic = existingCourse.getCourseIsPublic();

        isPublic = !isPublic;

        existingCourse.setCourseIsPublic(isPublic);
        return courseDAO.save(existingCourse);
    }

    public Page<CourseEntity> findCoursesByUserId(String kcUserKcUuid, Pageable pageable) {
        return courseDAO.findByKcUserKcUuid(kcUserKcUuid, pageable);
    }

    public SectionEntity createSection(SectionDTO sectionDTO) {
        SectionEntity newSection = new SectionEntity();
        newSection.setSectionDate(sectionDTO.getSectionDate());
        newSection.setSectionDescription(sectionDTO.getSectionDescription());
        newSection.setSectionName(sectionDTO.getSectionName());
        newSection.setStatus(sectionDTO.getStatus());
        
        logger.info("Validating that course exists");
        courseDAO.findById(sectionDTO.getCourseId()).ifPresent(newSection::setCourse);
        logger.info("Course found with id {}", sectionDTO.getCourseId());

        logger.info("Saving section to database");
        return sectionDAO.save(newSection);
    }

    public ContentEntity createContent(CreateContentRequestDTO createContentRequestDTO) {
        logger.info("Creating new content");
        ContentEntity newContent = new ContentEntity();
        newContent.setContentTitle(createContentRequestDTO.contentTitle);
        newContent.setStatus(true);

        //TODO actually validate
        logger.info("Validating that section exists");
        sectionDAO.findById(createContentRequestDTO.sectionId).ifPresent(newContent::setSection);
        logger.info("Section found with id {}", newContent.getSection().getSectionId());

        logger.info("Saving content to database");
        newContent.setContentId(contentDAO.save(newContent).getContentId());
        logger.info("Content saved with id {}", newContent.getContentId());

        logger.info("Starting to save attachements");
        List<AttachmentEntity> attachments = new ArrayList<AttachmentEntity>();
        for (AttachmentDTO  attachmentDTO : createContentRequestDTO.attachments) {
            AttachmentEntity newAttachment = new AttachmentEntity();
            newAttachment.setAttachment(attachmentDTO.getAttachment());
            newAttachment.setAttachmentName(attachmentDTO.getAttachmentName());
            newAttachment.setContent(newContent);
            newAttachment.setStatus(attachmentDTO.getStatus());

            newAttachment = attachmentDAO.save(newAttachment);
            newAttachment.setContent(null);
            attachments.add(newAttachment);
        }
        logger.info("finished saving attachments");

        newContent.setAttachments(attachments);
        return newContent;
    }

    public Page<CourseEntity> findCoursesByName(String courseName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseDAO.findByCourseNameIgnoreCaseContaining(courseName, pageable);
    }

    // alternativa para mapear:
    // public Page<Map<String, Object>> findCoursesByName(String courseName, int page, int size) {
    //     Pageable pageable = PageRequest.of(page, size);
    //     Page<CourseEntity> courseEntities = courseDAO.findByCourseNameIgnoreCaseContaining(courseName, pageable);
        
    //     return courseEntities.map(courseEntity -> {
    //         Map<String, Object> courseMap = new HashMap<>();
    //         courseMap.put("courseId", courseEntity.getCourseId());
    //         courseMap.put("courseDescription", courseEntity.getCourseDescription());
    //         // Agrega otros campos necesarios
    //         return courseMap;
    //     });
    // }

    //obtener id de usuario que creo un curso en base al id de curso:
    public String findUserIdByCourseId(Integer courseId) {
        CourseEntity courseEntity = courseDAO.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        return courseEntity.getKcUserKcUuid();
    }
}

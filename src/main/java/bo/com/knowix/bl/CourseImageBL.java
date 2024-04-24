package bo.com.knowix.bl;

import bo.com.knowix.dao.CourseImageDAO;
import bo.com.knowix.dto.CourseImageDTO;
import bo.com.knowix.entity.CourseImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseImageBL {

    private final CourseImageDAO courseImageDAO;

    @Autowired
    public CourseImageBL(CourseImageDAO courseImageDAO) {
        this.courseImageDAO = courseImageDAO;
    }

    @Transactional
    public CourseImageDTO saveCourseImage(CourseImageDTO dto, String imageFilePath) {
        List<CourseImageEntity> existingImages = courseImageDAO.findByCourseCourseId(dto.getCourseCourseId());
        if (!existingImages.isEmpty()) {
            existingImages.forEach(courseImageDAO::delete);
        }
        CourseImageEntity entity = new CourseImageEntity();
        entity.setImage(imageFilePath);
        entity.setCourseCourseId(dto.getCourseCourseId());
        courseImageDAO.save(entity);
        dto.setCourseImageId(entity.getCourseImageId());
        return dto;
    }

    public List<CourseImageDTO> findCourseImagesByCourseId(int courseId) {
        return courseImageDAO.findByCourseCourseId(courseId).stream()
                .map(entity -> new CourseImageDTO(entity.getCourseImageId(), entity.getImage(),
                        entity.getCourseCourseId()))
                .collect(Collectors.toList());
    }
}
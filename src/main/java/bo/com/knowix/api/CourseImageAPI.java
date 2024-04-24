package bo.com.knowix.api;

import bo.com.knowix.bl.CourseImageBL;
import bo.com.knowix.dto.CourseImageDTO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courseimage")
public class CourseImageAPI {

    private final CourseImageBL courseImageBL;

    @Autowired
    public CourseImageAPI(CourseImageBL courseImageBL) {
        this.courseImageBL = courseImageBL;
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<CourseImageDTO> uploadCourseImage(@RequestParam("image") MultipartFile image,
            @RequestParam("courseCourseId") int courseCourseId) {
        try {
            String filePath = saveImageFile(image, courseCourseId);
            CourseImageDTO courseImageDTO = new CourseImageDTO();
            courseImageDTO.setCourseCourseId(courseCourseId);
            CourseImageDTO created = courseImageBL.saveCourseImage(courseImageDTO, filePath);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private String saveImageFile(MultipartFile image, int courseId) throws IOException {
        String uploadDir = "bdd/images/";
        String fileName = "course_" + courseId + "_" + System.currentTimeMillis() + ".png";
        String filePath = uploadDir + fileName;
        File fileToSave = new File(filePath);
        FileUtils.writeByteArrayToFile(fileToSave, image.getBytes());
        return filePath;
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseImageDTO>> getCourseImagesByCourseId(@PathVariable int courseId) {
        try {
            List<CourseImageDTO> courseImages = courseImageBL.findCourseImagesByCourseId(courseId);
            if (courseImages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(courseImages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/course/{courseId}", consumes = { "multipart/form-data" })
    public ResponseEntity<CourseImageDTO> updateCourseImage(@PathVariable int courseId,
            @RequestParam("image") MultipartFile image) {
        try {
            String filePath = saveImageFile(image, courseId);
            CourseImageDTO courseImageDTO = new CourseImageDTO();
            courseImageDTO.setCourseCourseId(courseId);
            CourseImageDTO updated = courseImageBL.saveCourseImage(courseImageDTO, filePath);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

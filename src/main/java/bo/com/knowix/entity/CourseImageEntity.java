package bo.com.knowix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_image")
public class CourseImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_image_id")
    private Integer courseImageId;

    @Column(name = "image", nullable = false, length = 200)
    private String image;

    @Column(name = "Course_course_id", nullable = false)
    private Integer courseCourseId;

    public CourseImageEntity() {
    }

    public CourseImageEntity(Integer courseImageId, String image, Integer courseCourseId) {
        this.courseImageId = courseImageId;
        this.image = image;
        this.courseCourseId = courseCourseId;
    }

    // Getters and Setters

    public Integer getCourseImageId() {
        return courseImageId;
    }

    public void setCourseImageId(Integer courseImageId) {
        this.courseImageId = courseImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCourseCourseId() {
        return courseCourseId;
    }

    public void setCourseCourseId(Integer courseCourseId) {
        this.courseCourseId = courseCourseId;
    }


    @Override
    public String toString() {
        return "CourseImageEntity{" +
                "courseImageId=" + courseImageId +
                ", image='" + image + '\'' +
                ", courseCourseId=" + courseCourseId +
                '}';
    }
}

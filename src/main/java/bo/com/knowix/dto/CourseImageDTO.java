package bo.com.knowix.dto;

public class CourseImageDTO {
    private int courseImageId;
    private String image;
    private int courseCourseId;

    public CourseImageDTO() {
    }

    public CourseImageDTO(int courseImageId, String image, int courseCourseId) {
        this.courseImageId = courseImageId;
        this.image = image;
        this.courseCourseId = courseCourseId;
    }

    // Getters and Setters

    public int getCourseImageId() {
        return courseImageId;
    }

    public void setCourseImageId(int courseImageId) {
        this.courseImageId = courseImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCourseCourseId() {
        return courseCourseId;
    }

    public void setCourseCourseId(int courseCourseId) {
        this.courseCourseId = courseCourseId;
    }

    @Override
    public String toString() {
        return "CourseImageDTO{" +
                "courseImageId=" + courseImageId +
                ", image='" + image + '\'' +
                ", courseCourseId=" + courseCourseId +
                '}';
    }
}

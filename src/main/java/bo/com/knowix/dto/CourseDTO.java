package bo.com.knowix.dto;

public class CourseDTO {

    private int courseId;
    private String courseDescription;
    private double courseStandardPrice;
    private String courseName;
    private String courseRequirements;
    private String status;
    private int categoryCategoryId;
    private int languageLanguageId;
    private String kcUserKcUuid; // Campo para almacenar el UUID del usuario

    public CourseDTO() {
    }

    public CourseDTO(int courseId, String courseDescription, double courseStandardPrice, String courseName, String courseRequirements, String status, int categoryCategoryId, int languageLanguageId, String kcUserKcUuid) {
        this.courseId = courseId;
        this.courseDescription = courseDescription;
        this.courseStandardPrice = courseStandardPrice;
        this.courseName = courseName;
        this.courseRequirements = courseRequirements;
        this.status = status;
        this.categoryCategoryId = categoryCategoryId;
        this.languageLanguageId = languageLanguageId;
        this.kcUserKcUuid = kcUserKcUuid; // Inicializar el nuevo campo
    }


    public int getCourseId() {
        return courseId;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public double getCourseStandardPrice() {
        return courseStandardPrice;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseRequirements() {
        return courseRequirements;
    }

    public String getStatus() {
        return status;
    }

    public int getCategoryCategoryId() {
        return categoryCategoryId;
    }

    public int getLanguageLanguageId() {
        return languageLanguageId;
    }

    // Setters

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public void setCourseStandardPrice(double courseStandardPrice) {
        this.courseStandardPrice = courseStandardPrice;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseRequirements(String courseRequirements) {
        this.courseRequirements = courseRequirements;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategoryCategoryId(int categoryCategoryId) {
        this.categoryCategoryId = categoryCategoryId;
    }

    public void setLanguageLanguageId(int languageLanguageId) {
        this.languageLanguageId = languageLanguageId;
    }

    public String getKcUserKcUuid() {
        return kcUserKcUuid;
    }

    public void setKcUserKcUuid(String kcUserKcUuid) {
        this.kcUserKcUuid = kcUserKcUuid;
    }

    // toString
    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseId=" + courseId +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseStandardPrice=" + courseStandardPrice +
                ", courseName='" + courseName + '\'' +
                ", courseRequirements='" + courseRequirements + '\'' +
                ", status='" + status + '\'' +
                ", categoryCategoryId=" + categoryCategoryId +
                ", languageLanguageId=" + languageLanguageId +
                '}';
    }
}

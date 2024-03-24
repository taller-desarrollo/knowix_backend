package bo.com.knowix.dto;

public class CourseDTOResponse {

    private int courseId;
    private String courseDescription;
    private double courseStandardPrice;
    private String courseName;
    private String courseRequirements;
    private String status;
    private String categoryName; // Nombre de la categoría en lugar del ID
    private String languageName; // Nombre del lenguaje en lugar del ID
    private String kcUserKcUuid; // Campo para almacenar el UUID del usuario

    public CourseDTOResponse() {
    }

    public CourseDTOResponse(int courseId, String courseDescription, double courseStandardPrice, String courseName, String courseRequirements, String status, String categoryName, String languageName, String kcUserKcUuid) {
        this.courseId = courseId;
        this.courseDescription = courseDescription;
        this.courseStandardPrice = courseStandardPrice;
        this.courseName = courseName;
        this.courseRequirements = courseRequirements;
        this.status = status;
        this.categoryName = categoryName;
        this.languageName = languageName;
        this.kcUserKcUuid = kcUserKcUuid;
    }

    // Getters and Setters

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public double getCourseStandardPrice() {
        return courseStandardPrice;
    }

    public void setCourseStandardPrice(double courseStandardPrice) {
        this.courseStandardPrice = courseStandardPrice;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseRequirements() {
        return courseRequirements;
    }

    public void setCourseRequirements(String courseRequirements) {
        this.courseRequirements = courseRequirements;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getKcUserKcUuid() {
        return kcUserKcUuid;
    }

    public void setKcUserKcUuid(String kcUserKcUuid) {
        this.kcUserKcUuid = kcUserKcUuid;
    }

    @Override
    public String toString() {
        return "CourseDTOResponse{" +
                "courseId=" + courseId +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseStandardPrice=" + courseStandardPrice +
                ", courseName='" + courseName + '\'' +
                ", courseRequirements='" + courseRequirements + '\'' +
                ", status='" + status + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", languageName='" + languageName + '\'' +
                ", kcUserKcUuid='" + kcUserKcUuid + '\'' +
                '}';
    }
}

package bo.com.knowix.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "course_description", nullable = false, columnDefinition = "TEXT")
    private String courseDescription;

    @Column(name = "course_standard_price", nullable = false)
    private Double courseStandardPrice;

    @Column(name = "course_name", nullable = false, length = 100)
    private String courseName;

    @Column(name = "course_requirements", nullable = false, columnDefinition = "TEXT")
    private String courseRequirements;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "course_is_public", nullable = false)
    private boolean courseIsPublic;

    @ManyToOne
    @JoinColumn(name = "Category_category_id", referencedColumnName = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "language_language_id", referencedColumnName = "language_id")
    private LanguageEntity language;

    @JsonManagedReference
    @OneToMany(mappedBy = "course")
    private List<SectionEntity> sections;

    @Column(name = "kc_user_kc_uuid", nullable = false, length = 50)
    private String kcUserKcUuid;

    public CourseEntity() {
    }

    // Getters and Setters

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Double getCourseStandardPrice() {
        return courseStandardPrice;
    }

    public void setCourseStandardPrice(Double courseStandardPrice) {
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

    public boolean getCourseIsPublic() {
        return courseIsPublic;
    }

    public void setCourseIsPublic(boolean courseIsPublic) {
        this.courseIsPublic = courseIsPublic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    public List<SectionEntity> getSections() {
        return sections;
    }

    public void setSections(List<SectionEntity> sections) {
        this.sections = sections;
    }

    public String getKcUserKcUuid() {
        return kcUserKcUuid;
    }

    public void setKcUserKcUuid(String kcUserKcUuid) {
        this.kcUserKcUuid = kcUserKcUuid;
    }


    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseId=" + courseId +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseStandardPrice=" + courseStandardPrice +
                ", courseName='" + courseName + '\'' +
                ", courseRequirements='" + courseRequirements + '\'' +
                ", status='" + status + '\'' +
                ", category=" + (category != null ? category.getCategoryId() : "null") +
                ", language=" + (language != null ? language.getLanguageId() : "null") +
                ", sections=" + sections + 
                ", kcUserKcUuid='" + kcUserKcUuid + '\'' +
                '}';
    }
}

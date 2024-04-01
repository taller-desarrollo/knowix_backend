package bo.com.knowix.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Sections")
public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Integer sectionId;

    @Column(name = "section_name", nullable = false)
    private String sectionName;

    @Column(name = "section_description", nullable = false)
    private String sectionDescription;

    @Column(name = "section_date", nullable = false)
    private Date sectionDate;
    
    @Column(name = "status", nullable = false, length = 50)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "course_course_id", nullable = false)
    private CourseEntity course;

    // @OneToMany(mappedBy = "section")
    // private List<ContentEntity> contents;

    // Getters and Setters

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionDescription() {
        return sectionDescription;
    }

    public void setSectionDescription(String sectionDescription) {
        this.sectionDescription = sectionDescription;
    }

    public Date getSectionDate() {
        return sectionDate;
    }

    public void setSectionDate(Date sectionDate) {
        this.sectionDate = sectionDate;
    }
    
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    // public List<ContentEntity> getContents() {
    //     return contents;
    // }

    // public void setContents(List<ContentEntity> contents) {
    //     this.contents = contents;
    // }

    //TODO add to string
}
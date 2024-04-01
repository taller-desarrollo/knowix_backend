package bo.com.knowix.dto;

import java.util.Date;

public class SectionDTO {

    private Integer sectionId;
    private String sectionName;
    private String sectionDescription;
    private Date sectionDate;
    private Boolean status;
    private Integer courseId;

    // Constructors

    public SectionDTO() {}

    public SectionDTO(Integer sectionId, String sectionName, String sectionDescription, Date sectionDate, Boolean status, Integer courseId) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.sectionDescription = sectionDescription;
        this.sectionDate = sectionDate;
        this.status = status;
        this.courseId = courseId;
    }

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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
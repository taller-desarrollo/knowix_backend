package bo.com.knowix.dto;

import java.util.List;

public class ContentDTO {

    private Integer contentId;
    private String contentTitle;
    private String status;
    private List<AttachmentDTO> attachments;
    private Integer sectionId;

    // Constructors

    public ContentDTO() {}

    public ContentDTO(Integer contentId, String contentTitle, String status, List<AttachmentDTO> attachments, Integer sectionId) {
        this.contentId = contentId;
        this.contentTitle = contentTitle;
        this.status = status;
        this.attachments = attachments;
        this.sectionId = sectionId;
    }

    // Getters and Setters

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDTO> attachments) {
        this.attachments = attachments;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
}
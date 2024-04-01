package bo.com.knowix.dto;

public class AttachmentDTO {

    private Integer attachmentId;
    private String attachmentName;
    private byte[] attachment;
    private Integer contentId;
    private Boolean status;

    // Constructors

    public AttachmentDTO() {}

    public AttachmentDTO(Integer attachmentId, String attachmentName, byte[] attachment, Integer contentId, Boolean status) {
        this.attachmentId = attachmentId;
        this.attachmentName = attachmentName;
        this.attachment = attachment;
        this.contentId = contentId;
        this.status = status;
    }

    // Getters and Setters

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    // Override toString() if needed
}

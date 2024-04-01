package bo.com.knowix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Attachment")
public class AttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Integer attachmentId;

    @Column(name = "attachment_name")
    private String attachmentName;

    @Column(name = "attachment")
    private byte[] attachment;

    @ManyToOne
    @JoinColumn(name = "content_content_id", referencedColumnName = "content_id")
    private ContentEntity content;
    

    @Column(name = "status")
    private String status;

    public AttachmentEntity() {}

    //Getters and Setters

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
        return this.attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
    
    public ContentEntity getContent() {
        return content;
    }

    public void setContent(ContentEntity contentEntity) {
        this.content = contentEntity;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "AttachmentEntity{" +
               "attachmentId=" + attachmentId +
               ", attachmentName='" + attachmentName + '\'' + 
               ", attachment=" + attachment + 
               ", status='" + status + '\'' +
               '}';
    }
}
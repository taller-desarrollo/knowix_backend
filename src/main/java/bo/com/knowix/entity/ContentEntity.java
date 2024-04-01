package bo.com.knowix.entity;

import java.util.List;
import java.util.Set;

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
@Table(name = "Content")
public class ContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "content_title", nullable = false)
    private String contentTitle;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "content")
    private List<AttachmentEntity> attachments;

    @ManyToOne
    @JoinColumn(name = "sections_section_id", referencedColumnName = "section_id")
    private SectionEntity section;

    public ContentEntity() {}

    //Getters and Setters

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

    public List<AttachmentEntity> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentEntity> attachments) {
        this.attachments = attachments;
    }

    public SectionEntity getSection() {
        return section;
    }

    public void setSection(SectionEntity section) {
        this.section = section;
    }

    //TODO do to string
}
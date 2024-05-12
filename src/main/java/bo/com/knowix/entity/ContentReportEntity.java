package bo.com.knowix.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "content_report")
public class ContentReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_report_id")
    private int contentReportId;

    @Column(name = "content_report_reason", nullable = false, length = 500)
    private String contentReportReason;

    @Column(name = "content_report_date", nullable = false)
    private Timestamp contentReportDate;

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @ManyToOne
    @JoinColumn(name = "content_content_id", referencedColumnName = "content_id", nullable = false)
    private ContentEntity content;

    public ContentReportEntity(int contentReportId, String contentReportReason, Timestamp contentReportDate, String status, ContentEntity content) {
        this.contentReportId = contentReportId;
        this.contentReportReason = contentReportReason;
        this.contentReportDate = contentReportDate;
        this.status = status;
        this.content = content;
    }

    public ContentReportEntity() {
    }

    public int getContentReportId() {
        return contentReportId;
    }

    public void setContentReportId(int contentReportId) {
        this.contentReportId = contentReportId;
    }

    public String getContentReportReason() {
        return contentReportReason;
    }

    public void setContentReportReason(String contentReportReason) {
        this.contentReportReason = contentReportReason;
    }

    public Timestamp getContentReportDate() {
        return contentReportDate;
    }

    public void setContentReportDate(Timestamp contentReportDate) {
        this.contentReportDate = contentReportDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ContentEntity getContent() {
        return content;
    }

    public void setContent(ContentEntity content) {
        this.content = content;
    }
}

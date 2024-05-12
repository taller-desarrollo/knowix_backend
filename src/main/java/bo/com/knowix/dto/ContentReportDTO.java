package bo.com.knowix.dto;

import java.sql.Timestamp;

public class ContentReportDTO {
    private int contentReportId;
    private String contentReportReason;
    private Timestamp contentReportDate;
    private String status;
    private ContentDTO content;

    public ContentReportDTO(int contentReportId, String contentReportReason, Timestamp contentReportDate, String status, ContentDTO content) {
        this.contentReportId = contentReportId;
        this.contentReportReason = contentReportReason;
        this.contentReportDate = contentReportDate;
        this.status = status;
        this.content = content;
    }

    public ContentReportDTO() {
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

    public ContentDTO getContent() {
        return content;
    }

    public void setContent(ContentDTO content) {
        this.content = content;
    }
}

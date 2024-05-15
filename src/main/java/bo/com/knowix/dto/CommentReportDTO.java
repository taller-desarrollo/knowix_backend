package bo.com.knowix.dto;

import java.sql.Timestamp;

public class CommentReportDTO {
    private int commentReportId;
    private String commentReportReason;
    private Timestamp commentReportDate;

    private String status;
    private CommentDTO comment;

    public CommentReportDTO(int commentReportId, String commentReportReason, Timestamp commentReportDate, String status, CommentDTO comment) {
        this.commentReportId = commentReportId;
        this.commentReportReason = commentReportReason;
        this.commentReportDate = commentReportDate;
        this.status = status;
        this.comment = comment;
    }

    public CommentReportDTO() {
    }

    public int getCommentReportId() {
        return commentReportId;
    }

    public void setCommentReportId(int commentReportId) {
        this.commentReportId = commentReportId;
    }

    public String getCommentReportReason() {
        return commentReportReason;
    }

    public void setCommentReportReason(String commentReportReason) {
        this.commentReportReason = commentReportReason;
    }

    public Timestamp getCommentReportDate() {
        return commentReportDate;
    }

    public void setCommentReportDate(Timestamp commentReportDate) {
        this.commentReportDate = commentReportDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CommentDTO getComment() {
        return comment;
    }

    public void setComment(CommentDTO comment) {
        this.comment = comment;
    }


}

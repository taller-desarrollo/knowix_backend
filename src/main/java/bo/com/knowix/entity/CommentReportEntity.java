package bo.com.knowix.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "comment_report")
public class CommentReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_report_id")
    private int commentReportId;

    @Column(name = "comment_report_reason", nullable = false, length = 500)
    private String commentReportReason;

    @Column(name = "comment_report_date", nullable = false)
    private Timestamp commentReportDate;

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @ManyToOne
    @JoinColumn(name = "comment_comment_id", referencedColumnName = "comment_id", nullable = false)
    private CommentEntity comment;

    public CommentReportEntity(int commentReportId, String commentReportReason, Timestamp commentReportDate, String status, CommentEntity comment) {
        this.commentReportId = commentReportId;
        this.commentReportReason = commentReportReason;
        this.commentReportDate = commentReportDate;
        this.status = status;
        this.comment = comment;
    }

    public CommentReportEntity() {

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

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
    }
}

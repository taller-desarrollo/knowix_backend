package bo.com.knowix.dto;

import java.sql.Timestamp;

public class CommentDTO {
    private int commentId;
    private String content;
    private Timestamp creationDate;
    private boolean status;
    private int courseId;
    private String kcUserKcUuid;
    private Integer parentCommentId; // Se usa Integer porque puede ser nulo

    public CommentDTO() {
    }

    public CommentDTO(int commentId, String content, Timestamp creationDate, boolean status, int courseId, String kcUserKcUuid, Integer parentCommentId) {
        this.commentId = commentId;
        this.content = content;
        this.creationDate = creationDate;
        this.status = status;
        this.courseId = courseId;
        this.kcUserKcUuid = kcUserKcUuid;
        this.parentCommentId = parentCommentId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getKcUserKcUuid() {
        return kcUserKcUuid;
    }

    public void setKcUserKcUuid(String kcUserKcUuid) {
        this.kcUserKcUuid = kcUserKcUuid;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", status=" + status +
                ", courseId=" + courseId +
                ", kcUserKcUuid='" + kcUserKcUuid + '\'' +
                ", parentCommentId=" + parentCommentId +
                '}';
    }
}

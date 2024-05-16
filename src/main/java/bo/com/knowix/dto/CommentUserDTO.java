package bo.com.knowix.dto;

import java.sql.Timestamp;

public class CommentUserDTO {
    private int commentId;
    private String content;
    private Timestamp creationDate;
    private boolean status;
    private int courseId;
    private String kcUserKcUuid;
    private Integer parentCommentId;
    private String firstName;
    private String lastName;
    private String email;

    public CommentUserDTO() {}

    public CommentUserDTO(int commentId, String content, Timestamp creationDate, boolean status, int courseId, String kcUserKcUuid, Integer parentCommentId, String firstName, String lastName, String email) {
        this.commentId = commentId;
        this.content = content;
        this.creationDate = creationDate;
        this.status = status;
        this.courseId = courseId;
        this.kcUserKcUuid = kcUserKcUuid;
        this.parentCommentId = parentCommentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters and Setters for all fields

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

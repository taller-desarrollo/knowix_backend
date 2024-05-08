package bo.com.knowix.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "course_course_id", referencedColumnName = "course_id", nullable = false)
    private CourseEntity course;

    @Column(name = "kc_user_kc_uuid", nullable = false, length = 50)
    private String kcUserKcUuid;

    @ManyToOne
    @JoinColumn(name = "comment_parent_id", referencedColumnName = "comment_id")
    private CommentEntity parentComment;

    public CommentEntity() {
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

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public String getKcUserKcUuid() {
        return kcUserKcUuid;
    }

    public void setKcUserKcUuid(String kcUserKcUuid) {
        this.kcUserKcUuid = kcUserKcUuid;
    }

    public CommentEntity getParentComment() {
        return parentComment;
    }

    public void setParentComment(CommentEntity parentComment) {
        this.parentComment = parentComment;
    }
}

package bo.com.knowix.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Verification_Request")
public class VerificationRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_request_id")
    private Long id;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "state", nullable = false, length = 30)
    private String state;

    @Column(name = "additional_comment", nullable = true)
    private String additionalComment;

    @Column(name = "request_date", nullable = false)
    private Date requestDate;

    @ManyToOne
    @JoinColumn(name = "kc_user_kc_uuid")
    private KcUserEntity kcUserEntity;

    public KcUserEntity getKcUserEntity() {
        return kcUserEntity;
    }

    public void setKcUserEntity(KcUserEntity kcUserEntity) {
        this.kcUserEntity = kcUserEntity;
    }

    public VerificationRequestEntity() {
    }

    public VerificationRequestEntity(Long id, boolean status, String state, String additionalComment, Date requestDate, KcUserEntity kcUserEntity) {
        this.id = id;
        this.status = status;
        this.state = state;
        this.additionalComment = additionalComment;
        this.requestDate = requestDate;
        this.kcUserEntity = kcUserEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAdditionalComment() {
        return additionalComment;
    }

    public void setAdditionalComment(String additionalComment) {
        this.additionalComment = additionalComment;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

}

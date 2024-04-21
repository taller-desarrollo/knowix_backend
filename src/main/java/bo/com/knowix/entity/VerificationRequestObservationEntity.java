package bo.com.knowix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Verification_Request_Observation")
public class VerificationRequestObservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_request_observation_id")
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Verification_Request_verification_request_id", nullable = false)
    private VerificationRequestEntity verificationRequest;

    public VerificationRequestObservationEntity() {
    }

    public VerificationRequestObservationEntity(Long id, String content, String title) {
        this.id = id;
        this.content = content;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public VerificationRequestEntity getVerificationRequest() {
        return verificationRequest;
    }

    public void setVerificationRequest(VerificationRequestEntity verificationRequest) {
        this.verificationRequest = verificationRequest;
    }
}

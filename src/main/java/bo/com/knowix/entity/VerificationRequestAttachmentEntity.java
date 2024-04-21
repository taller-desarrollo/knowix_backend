package bo.com.knowix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Verification_Request_Attachment")
public class VerificationRequestAttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_request_attachment_id")
    private Long id;

    @Column(name = "s3_object_id", nullable = false)
    private int s3ObjectId;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "school_or_institution", nullable = false, length = 100)
    private String schoolOrInstitution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verification_request_verification_request_id", nullable = false)
    private VerificationRequestEntity verificationRequest;

    public VerificationRequestAttachmentEntity() {
    }

    public VerificationRequestAttachmentEntity(Long id, int s3ObjectId, String title, String description, String schoolOrInstitution) {
        this.id = id;
        this.s3ObjectId = s3ObjectId;
        this.title = title;
        this.description = description;
        this.schoolOrInstitution = schoolOrInstitution;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getS3ObjectId() {
        return s3ObjectId;
    }

    public void setS3ObjectId(int s3ObjectId) {
        this.s3ObjectId = s3ObjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchoolOrInstitution() {
        return schoolOrInstitution;
    }

    public void setSchoolOrInstitution(String schoolOrInstitution) {
        this.schoolOrInstitution = schoolOrInstitution;
    }

    public VerificationRequestEntity getVerificationRequest() {
        return verificationRequest;
    }

    public void setVerificationRequest(VerificationRequestEntity verificationRequest) {
        this.verificationRequest = verificationRequest;
    }
}

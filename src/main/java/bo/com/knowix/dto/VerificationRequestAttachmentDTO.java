package bo.com.knowix.dto;

public class VerificationRequestAttachmentDTO {
    private Long id;
    private S3ObjectDTO s3ObjectDTO;
    private String title;
    private String description;
    private String schoolOrInstitution;

    public VerificationRequestAttachmentDTO() {
    }

    public VerificationRequestAttachmentDTO(Long id, S3ObjectDTO s3ObjectDTO, String title, String description, String schoolOrInstitution) {
        this.id = id;
        this.s3ObjectDTO = s3ObjectDTO;
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

    public S3ObjectDTO getS3ObjectDTO() {
        return s3ObjectDTO;
    }

    public void setS3ObjectDTO(S3ObjectDTO s3ObjectDTO) {
        this.s3ObjectDTO = s3ObjectDTO;
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
}

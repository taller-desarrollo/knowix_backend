package bo.com.knowix.dto;

public class VerificationRequestObservationDTO {
    private Long id;
    private String content;
    private String title;

    public VerificationRequestObservationDTO() {
    }

    public VerificationRequestObservationDTO(Long id, String content, String title) {
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
}

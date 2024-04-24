package bo.com.knowix.dto;

public class S3ObjectDTO {
    private Long id;
    private String filename;
    private String url;
    private String type;

    public S3ObjectDTO() {
    }

    public S3ObjectDTO(Long id, String filename, String url, String type) {
        this.id = id;
        this.filename = filename;
        this.url = url;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

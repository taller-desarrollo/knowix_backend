package bo.com.knowix.dto;

public class SocialMediaDTO {

    private int socialMediaId;
    private String kcUserUuid;
    private String socialMediaUrl;
    private boolean status;

    public SocialMediaDTO() {
    }

    public SocialMediaDTO(int socialMediaId, String kcUserUuid, String socialMediaUrl, boolean status) {
        this.socialMediaId = socialMediaId;
        this.kcUserUuid = kcUserUuid;
        this.socialMediaUrl = socialMediaUrl;
        this.status = status;
    }

    public int getSocialMediaId() {
        return socialMediaId;
    }

    public void setSocialMediaId(int socialMediaId) {
        this.socialMediaId = socialMediaId;
    }

    public String getKcUserUuid() {
        return kcUserUuid;
    }

    public void setKcUserUuid(String kcUserUuid) {
        this.kcUserUuid = kcUserUuid;
    }

    public String getSocialMediaUrl() {
        return socialMediaUrl;
    }

    public void setSocialMediaUrl(String socialMediaUrl) {
        this.socialMediaUrl = socialMediaUrl;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SocialMediaDTO{" +
                "socialMediaId=" + socialMediaId +
                ", kcUserUuid='" + kcUserUuid + '\'' +
                ", socialMediaUrl='" + socialMediaUrl + '\'' +
                ", status=" + status +
                '}';
    }
}
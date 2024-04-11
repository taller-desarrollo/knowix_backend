package bo.com.knowix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_social_media")
public class SocialMediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_media_id")
    private Integer socialMediaId;

    @Column(name = "kc_user_uuid", nullable = false, length = 50)
    private String kcUserUuid;

    @Column(name = "social_media_url", nullable = false)
    private String socialMediaUrl;

    @Column(name = "status", nullable = false)
    private Boolean status;

    public SocialMediaEntity() {
    }

    // Getters and Setters

    public Integer getSocialMediaId() {
        return socialMediaId;
    }

    public void setSocialMediaId(Integer socialMediaId) {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SocialMedia{" +
                "socialMediaId=" + socialMediaId +
                ", kcUserUuid='" + kcUserUuid + '\'' +
                ", socialMediaUrl='" + socialMediaUrl + '\'' +
                ", status=" + status +
                '}';
    }
}

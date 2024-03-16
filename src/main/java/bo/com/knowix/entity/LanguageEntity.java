package bo.com.knowix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "language")
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Integer languageId;

    @Column(name = "language_name", nullable = false, length = 100)
    private String languageName;

    @Column(name = "status", nullable = false)
    private Boolean status;

    public LanguageEntity() {
    }

    public LanguageEntity(Integer languageId, String languageName, Boolean status) {
        this.languageId = languageId;
        this.languageName = languageName;
        this.status = status;
    }

    // Getters

    public Integer getLanguageId() {
        return languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public Boolean getStatus() {
        return status;
    }

    // Setters

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LanguageEntity{" +
                "languageId=" + languageId +
                ", languageName='" + languageName + '\'' +
                ", status=" + status +
                '}';
    }
}

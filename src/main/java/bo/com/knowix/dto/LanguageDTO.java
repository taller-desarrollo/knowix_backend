package bo.com.knowix.dto;

public class LanguageDTO {

    private int languageId;
    private String languageName;
    private boolean status;

    public LanguageDTO() {
    }

    public LanguageDTO(int languageId, String languageName, boolean status) {
        this.languageId = languageId;
        this.languageName = languageName;
        this.status = status;
    }

    // Getters

    public int getLanguageId() {
        return languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public boolean isStatus() {
        return status;
    }

    // Setters

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // toString
    @Override
    public String toString() {
        return "LanguageDTO{" +
                "languageId=" + languageId +
                ", languageName='" + languageName + '\'' +
                ", status=" + status +
                '}';
    }
}

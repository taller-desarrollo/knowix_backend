package bo.com.knowix.dto;

public class AccountTypeDTO {

    private int accountTypeId;
    private String description;

    public AccountTypeDTO() {
    }

    public AccountTypeDTO(int accountTypeId, String description) {
        this.accountTypeId = accountTypeId;
        this.description = description;
    }

    // Getters

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public String getDescription() {
        return description;
    }

    // Setters

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString

    @Override
    public String toString() {
        return "AccountTypeDTO{" +
                "accountTypeId=" + accountTypeId +
                ", description='" + description + '\'' +
                '}';
    }
}

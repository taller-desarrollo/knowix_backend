package bo.com.knowix.dto;

public class BankDTO {

    private int bankId;
    private String bankName;
    private String phoneNumber;
    private String webpage;

    public BankDTO() {
    }

    public BankDTO(int bankId, String bankName, String phoneNumber, String webpage) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.phoneNumber = phoneNumber;
        this.webpage = webpage;
    }

    // Getters

    public int getBankId() {
        return bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebpage() {
        return webpage;
    }

    // Setters

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    // toString

    @Override
    public String toString() {
        return "BankDTO{" +
                "bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", webpage='" + webpage + '\'' +
                '}';
    }
}

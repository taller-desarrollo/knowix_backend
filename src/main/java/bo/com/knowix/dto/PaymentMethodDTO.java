package bo.com.knowix.dto;

public class PaymentMethodDTO {
    private int paymentMethodId;
    private String ciPerson;
    private String nameOwner;
    private String phoneNumber;
    private String qrImage;
    private int accountNumber;
    private int bankBankId;
    private String kcUserKcUuid;
    private int accountTypeAccountTypeId;

    public PaymentMethodDTO() {
    }

    public PaymentMethodDTO(int paymentMethodId, String ciPerson, String nameOwner, String phoneNumber, String qrImage, int accountNumber, int bankBankId, String kcUserKcUuid, int accountTypeAccountTypeId) {
        this.paymentMethodId = paymentMethodId;
        this.ciPerson = ciPerson;
        this.nameOwner = nameOwner;
        this.phoneNumber = phoneNumber;
        this.qrImage = qrImage;
        this.accountNumber = accountNumber;
        this.bankBankId = bankBankId;
        this.kcUserKcUuid = kcUserKcUuid;
        this.accountTypeAccountTypeId = accountTypeAccountTypeId;
    }

    // Getters and Setters

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getCiPerson() {
        return ciPerson;
    }

    public void setCiPerson(String ciPerson) {
        this.ciPerson = ciPerson;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQrImage() {
        return qrImage;
    }

    public void setQrImage(String qrImage) {
        this.qrImage = qrImage;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBankBankId() {
        return bankBankId;
    }

    public void setBankBankId(int bankBankId) {
        this.bankBankId = bankBankId;
    }

    public String getKcUserKcUuid() {
        return kcUserKcUuid;
    }

    public void setKcUserKcUuid(String kcUserKcUuid) {
        this.kcUserKcUuid = kcUserKcUuid;
    }

    public int getAccountTypeAccountTypeId() {
        return accountTypeAccountTypeId;
    }

    public void setAccountTypeAccountTypeId(int accountTypeAccountTypeId) {
        this.accountTypeAccountTypeId = accountTypeAccountTypeId;
    }

    @Override
    public String toString() {
        return "PaymentMethodDTO{" +
                "paymentMethodId=" + paymentMethodId +
                ", ciPerson='" + ciPerson + '\'' +
                ", nameOwner='" + nameOwner + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", qrImage='" + qrImage + '\'' +
                ", accountNumber=" + accountNumber +
                ", bankBankId=" + bankBankId +
                ", kcUserKcUuid='" + kcUserKcUuid + '\'' +
                ", accountTypeAccountTypeId=" + accountTypeAccountTypeId +
                '}';
    }
}

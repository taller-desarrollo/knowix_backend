package bo.com.knowix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import bo.com.knowix.entity.AccountTypeEntity;
import bo.com.knowix.entity.BankEntity;

@Entity
@Table(name = "payment_method")
public class PaymentMethodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private Integer paymentMethodId;

    @Column(name = "ci_person", nullable = false, length = 30)
    private String ciPerson;

    @Column(name = "name_owner", nullable = false, length = 30)
    private String nameOwner;

    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;

    @Column(name = "qr_image", nullable = false, length = 300000)
    private String qrImage;

    @Column(name = "account_number", nullable = false, length = 50)
    private String accountNumber;

    @Column(name = "bank_bank_id", nullable = false)
    private Integer bankBankId;

    @Column(name = "kc_user_kc_uuid", nullable = false, length = 50)
    private String kcUserKcUuid;

    @Column(name = "account_type_account_type_id", nullable = false)
    private Integer accountTypeAccountTypeId;

    public PaymentMethodEntity() {
    }

    public PaymentMethodEntity(Integer paymentMethodId, String ciPerson, String nameOwner, String phoneNumber, String qrImage, String accountNumber, Integer bankBankId, String kcUserKcUuid, Integer accountTypeAccountTypeId) {
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

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getBankBankId() {
        return bankBankId;
    }

    public void setBankBankId(Integer bankBankId) {
        this.bankBankId = bankBankId;
    }

    public String getKcUserKcUuid() {
        return kcUserKcUuid;
    }

    public void setKcUserKcUuid(String kcUserKcUuid) {
        this.kcUserKcUuid = kcUserKcUuid;
    }

    public Integer getAccountTypeAccountTypeId() {
        return accountTypeAccountTypeId;
    }

    public void setAccountTypeAccountTypeId(Integer accountTypeAccountTypeId) {
        this.accountTypeAccountTypeId = accountTypeAccountTypeId;
    }


    @Override
    public String toString() {
        return "PaymentMethodEntity{" +
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

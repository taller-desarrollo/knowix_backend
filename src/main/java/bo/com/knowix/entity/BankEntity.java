package bo.com.knowix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank")
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "bank_name", nullable = false, length = 30)
    private String bankName;

    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;

    @Column(name = "webpage", nullable = false, length = 30)
    private String webpage;

    public BankEntity() {
    }

    public BankEntity(Integer bankId, String bankName, String phoneNumber, String webpage) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.phoneNumber = phoneNumber;
        this.webpage = webpage;
    }

    // Getters and Setters

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    @Override
    public String toString() {
        return "BankEntity{" +
                "bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", webpage='" + webpage + '\'' +
                '}';
    }
}

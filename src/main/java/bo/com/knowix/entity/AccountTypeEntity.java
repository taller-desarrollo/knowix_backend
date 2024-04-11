package bo.com.knowix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_type")
public class AccountTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id")
    private Integer accountTypeId;

    @Column(name = "description", nullable = false, length = 30)
    private String description;

    public AccountTypeEntity() {
    }

    public AccountTypeEntity(Integer accountTypeId, String description) {
        this.accountTypeId = accountTypeId;
        this.description = description;
    }

    // Getters and Setters

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AccountTypeEntity{" +
                "accountTypeId=" + accountTypeId +
                ", description='" + description + '\'' +
                '}';
    }
}

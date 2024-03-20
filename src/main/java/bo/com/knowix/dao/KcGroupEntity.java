package bo.com.knowix.dao;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "kc_group")
public class KcGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kc_group_id")
    private Long kcGroupId;

    @Column(name = "kc_group_name", length = 50, nullable = false)
    private String kcGroupName;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_date", nullable = false)
    private Timestamp txDate;

    @Column(name = "tx_user", length = 50, nullable = false)
    private String txUser;

    @Column(name = "tx_host", length = 50, nullable = false)
    private String txHost;

    // Getters and Setters

    public Long getKcGroupId() {
        return kcGroupId;
    }

    public void setKcGroupId(Long kcGroupId) {
        this.kcGroupId = kcGroupId;
    }

    public String getKcGroupName() {
        return kcGroupName;
    }

    public void setKcGroupName(String kcGroupName) {
        this.kcGroupName = kcGroupName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getTxDate() {
        return txDate;
    }

    public void setTxDate(Timestamp txDate) {
        this.txDate = txDate;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public KcGroupEntity() {
    }

    public KcGroupEntity(Long kcGroupId, String kcGroupName, boolean status, Timestamp txDate, String txUser, String txHost) {
        this.kcGroupId = kcGroupId;
        this.kcGroupName = kcGroupName;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
    }

    public String toString() {
        return "KcGroup{" +
                "kcGroupId=" + kcGroupId +
                ", kcGroupName='" + kcGroupName + '\'' +
                ", status=" + status +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost='" + txHost + '\'' +
                '}';
    }
}

package bo.com.knowix.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kc_user")
public class KcUserEntity {
    @Id
    @Column(name = "kc_uuid", length = 50, nullable = false)
    private String kcUuid;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_date", nullable = false)
    private Timestamp txDate;

    @Column(name = "tx_user", length = 50, nullable = false)
    private String txUser;

    @Column(name = "tx_host", length = 50, nullable = false)
    private String txHost;

    @Column(name = "s3_profile_picture", nullable = false)
    private int s3ProfilePicture;

    @Column(name = "is_verified", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean verified;

    @ManyToOne
    @JoinColumn(name = "kc_group_kc_group_id", nullable = false)
    private KcGroupEntity kcGroup;

    public String getKcUuid() {
        return kcUuid;
    }

    public void setKcUuid(String kcUuid) {
        this.kcUuid = kcUuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getS3ProfilePicture() {
        return s3ProfilePicture;
    }

    public void setS3ProfilePicture(int s3ProfilePicture) {
        this.s3ProfilePicture = s3ProfilePicture;
    }

    public KcGroupEntity getKcGroup() {
        return kcGroup;
    }

    public void setKcGroup(KcGroupEntity kcGroup) {
        this.kcGroup = kcGroup;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public KcUserEntity() {
    }

    public KcUserEntity(String kcUuid, String firstName, String lastName, String email, boolean status, Timestamp txDate, String txUser, String txHost, int s3ProfilePicture, KcGroupEntity kcGroup, boolean verified) {
        this.kcUuid = kcUuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.txDate = txDate;
        this.txUser = txUser;
        this.txHost = txHost;
        this.s3ProfilePicture = s3ProfilePicture;
        this.kcGroup = kcGroup;
        this.verified = verified;
    }

    public String toString() {
        return "KcUserEntity{" +
                "kcUuid='" + kcUuid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email=" + email +
                ", status=" + status +
                ", txDate=" + txDate +
                ", txUser='" + txUser + '\'' +
                ", txHost='" + txHost + '\'' +
                ", s3ProfilePicture=" + s3ProfilePicture +
                ", kcGroup=" + kcGroup +
                '}';
    }
}

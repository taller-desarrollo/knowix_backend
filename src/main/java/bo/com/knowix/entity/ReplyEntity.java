package bo.com.knowix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Integer replyId;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "coment", nullable = false, length = 500)
    private String coment;

    @ManyToOne
    @JoinColumn(name = "purchase_purchase_id", nullable = false)
    private PurchaseEntity purchase;

    public ReplyEntity() {
    }

    // Getters and Setters

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public PurchaseEntity getPurchase() {
        return purchase;
    }

    public void setPurchase(PurchaseEntity purchase) {
        this.purchase = purchase;
    }

    @Override
    public String toString() {
        return "ReplyEntity{" +
                "replyId=" + replyId +
                ", status=" + status +
                ", date=" + date +
                ", coment='" + coment + '\'' +
                ", purchase=" + purchase +
                '}';
    }
}

package bo.com.knowix.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private int purchaseId;

    @Column(name = "date_purchase", nullable = false)
    private Timestamp datePurchase;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "image_comprobante", nullable = false, length = 255)
    private String imageComprobante;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "payment_method_id")
    private PaymentMethodEntity paymentMethod;

    @Column(name = "kc_user_kc_uuid", nullable = false, length = 50)
    private String kcUserKcUuid;

    public PurchaseEntity() {
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Timestamp getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Timestamp datePurchase) {
        this.datePurchase = datePurchase;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getImageComprobante() {
        return imageComprobante;
    }

    public void setImageComprobante(String imageComprobante) {
        this.imageComprobante = imageComprobante;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public PaymentMethodEntity getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEntity paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getKcUserKcUuid() {
        return kcUserKcUuid;
    }

    public void setKcUserKcUuid(String kcUserKcUuid) {
        this.kcUserKcUuid = kcUserKcUuid;
    }

    @Override
    public String toString() {
        return "PurchaseEntity{" +
                "purchaseId=" + purchaseId +
                ", datePurchase=" + datePurchase +
                ", amount=" + amount +
                ", imageComprobante='" + imageComprobante + '\'' +
                ", course=" + (course != null ? course.getCourseId() : "null") +
                ", paymentMethod=" + (paymentMethod != null ? paymentMethod.getPaymentMethodId() : "null") +
                ", kcUserKcUuid='" + kcUserKcUuid + '\'' +
                '}';
    }
}

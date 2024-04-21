package bo.com.knowix.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import bo.com.knowix.dto.CourseDTO;
import bo.com.knowix.dto.PaymentMethodDTO;

public class PurchaseDTO {

    private int purchaseId;
    private Timestamp datePurchase;
    private BigDecimal amount;
    private String imageComprobante;
    private CourseDTO course;
    private PaymentMethodDTO paymentMethod;
    private String kcUserKcUuid;

    public PurchaseDTO() {
    }

    public PurchaseDTO(int purchaseId, Timestamp datePurchase, BigDecimal amount, String imageComprobante, CourseDTO course, PaymentMethodDTO paymentMethod, String kcUserKcUuid) {
        this.purchaseId = purchaseId;
        this.datePurchase = datePurchase;
        this.amount = amount;
        this.imageComprobante = imageComprobante;
        this.course = course;
        this.paymentMethod = paymentMethod;
        this.kcUserKcUuid = kcUserKcUuid;
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

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getKcUser() {
        return kcUserKcUuid;
    }

    public void setKcUser(String kcUserKcUuid) {
        this.kcUserKcUuid = kcUserKcUuid;
    }

    @Override
    public String toString() {
        return "PurchaseDTO{" +
                "purchaseId=" + purchaseId +
                ", datePurchase=" + datePurchase +
                ", amount=" + amount +
                ", imageComprobante='" + imageComprobante + '\'' +
                ", course=" + course +
                ", paymentMethod=" + paymentMethod +
                ", kcUser=" + kcUserKcUuid +
                '}';
    }
}

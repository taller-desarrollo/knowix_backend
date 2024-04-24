package bo.com.knowix.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PurchaseDTO {

    private int purchaseId;
    private Timestamp datePurchase;
    private BigDecimal amount;
    private String imageComprobant; // Corregido para reflejar el nombre correcto de la columna en la base de datos
    private CourseDTO course;
    private PaymentMethodDTO paymentMethod;
    private String kcUserKcUuid;

    public PurchaseDTO() {
    }

    public PurchaseDTO(int purchaseId, Timestamp datePurchase, BigDecimal amount, String imageComprobant, CourseDTO course, PaymentMethodDTO paymentMethod, String kcUserKcUuid) {
        this.purchaseId = purchaseId;
        this.datePurchase = datePurchase;
        this.amount = amount;
        this.imageComprobant = imageComprobant;
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

    public String getImageComprobant() {
        return imageComprobant;
    }

    public void setImageComprobant(String imageComprobant) {
        this.imageComprobant = imageComprobant;
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

    public String getKcUserKcUuid() {
        return kcUserKcUuid;
    }

    public void setKcUserKcUuid(String kcUserKcUuid) {
        this.kcUserKcUuid = kcUserKcUuid;
    }

    @Override
    public String toString() {
        return "PurchaseDTO{" +
                "purchaseId=" + purchaseId +
                ", datePurchase=" + datePurchase +
                ", amount=" + amount +
                ", imageComprobant='" + imageComprobant + '\'' +
                ", course=" + course +
                ", paymentMethod=" + paymentMethod +
                ", kcUser='" + kcUserKcUuid + '\'' +
                '}';
    }
}

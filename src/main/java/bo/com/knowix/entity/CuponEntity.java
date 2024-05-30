package bo.com.knowix.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cupon")
public class CuponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cupon_id")
    private Integer cuponId;

    @Column(name = "discount_type", nullable = false, length = 200)
    private String discountType;

    @Column(name = "discount_amount", nullable = false, precision = 10, scale = 5)
    private BigDecimal discountAmount;

    @Column(name = "cupon_code", nullable = false, length = 500)
    private String cuponCode;

    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;

    @Column(name = "min_amount_purchase", nullable = false, precision = 10, scale = 5)
    private BigDecimal minAmountPurchase;

    @Column(name = "description_promotion", nullable = false, length = 200)
    private String descriptionPromotion;

    public CuponEntity() {
    }

    public Integer getCuponId() {
        return cuponId;
    }

    public void setCuponId(Integer cuponId) {
        this.cuponId = cuponId;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getCuponCode() {
        return cuponCode;
    }

    public void setCuponCode(String cuponCode) {
        this.cuponCode = cuponCode;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getMinAmountPurchase() {
        return minAmountPurchase;
    }

    public void setMinAmountPurchase(BigDecimal minAmountPurchase) {
        this.minAmountPurchase = minAmountPurchase;
    }

    public String getDescriptionPromotion() {
        return descriptionPromotion;
    }

    public void setDescriptionPromotion(String descriptionPromotion) {
        this.descriptionPromotion = descriptionPromotion;
    }

    @Override
    public String toString() {
        return "CuponEntity{" +
                "cuponId=" + cuponId +
                ", discountType='" + discountType + '\'' +
                ", discountAmount=" + discountAmount +
                ", cuponCode='" + cuponCode + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", minAmountPurchase=" + minAmountPurchase +
                ", descriptionPromotion='" + descriptionPromotion + '\'' +
                '}';
    }
}

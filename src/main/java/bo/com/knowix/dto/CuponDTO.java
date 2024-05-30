package bo.com.knowix.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CuponDTO {

    private int cuponId;
    private String discountType;
    private BigDecimal discountAmount;
    private String cuponCode;
    private Timestamp startDate;
    private Timestamp endDate;
    private BigDecimal minAmountPurchase;
    private String descriptionPromotion;

    public CuponDTO() {
    }

    public CuponDTO(int cuponId, String discountType, BigDecimal discountAmount, String cuponCode, Timestamp startDate, Timestamp endDate, BigDecimal minAmountPurchase, String descriptionPromotion) {
        this.cuponId = cuponId;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.cuponCode = cuponCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minAmountPurchase = minAmountPurchase;
        this.descriptionPromotion = descriptionPromotion;
    }

    public int getCuponId() {
        return cuponId;
    }

    public void setCuponId(int cuponId) {
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
        return "CuponDTO{" +
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

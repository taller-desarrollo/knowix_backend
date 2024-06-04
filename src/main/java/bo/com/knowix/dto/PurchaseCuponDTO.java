package bo.com.knowix.dto;

public class PurchaseCuponDTO {
    
    private Integer purchaseCuponId;
    private Integer purchasePurchaseId;
    private Integer cuponCuponId;

    public PurchaseCuponDTO() {
    }

    public PurchaseCuponDTO(Integer purchaseCuponId, Integer purchasePurchaseId, Integer cuponCuponId) {
        this.purchaseCuponId = purchaseCuponId;
        this.purchasePurchaseId = purchasePurchaseId;
        this.cuponCuponId = cuponCuponId;
    }

    public Integer getPurchaseCuponId() {
        return purchaseCuponId;
    }

    public void setPurchaseCuponId(Integer purchaseCuponId) {
        this.purchaseCuponId = purchaseCuponId;
    }

    public Integer getPurchasePurchaseId() {
        return purchasePurchaseId;
    }

    public void setPurchasePurchaseId(Integer purchasePurchaseId) {
        this.purchasePurchaseId = purchasePurchaseId;
    }

    public Integer getCuponCuponId() {
        return cuponCuponId;
    }

    public void setCuponCuponId(Integer cuponCuponId) {
        this.cuponCuponId = cuponCuponId;
    }

    @Override
    public String toString() {
        return "PurchaseCuponDTO{" + "purchaseCuponId=" + purchaseCuponId + ", purchasePurchaseId=" + purchasePurchaseId + ", cuponCuponId=" + cuponCuponId + '}';
    }
}

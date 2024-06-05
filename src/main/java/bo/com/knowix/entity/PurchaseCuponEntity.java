package bo.com.knowix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase_cupon")
public class PurchaseCuponEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_cupon_id")
    private Integer purchaseCuponId;
    
    @Column(name = "purchase_purchase_id", nullable = false)
    private Integer purchasePurchaseId;

    @Column(name = "cupon_cupon_id", nullable = false)
    private Integer cuponCuponId;

    public PurchaseCuponEntity() {
    }

    public PurchaseCuponEntity(Integer purchaseCuponId, Integer purchasePurchaseId, Integer cuponCuponId) {
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
        return "PurchaseCuponEntity{" + "purchaseCuponId=" + purchaseCuponId + ", purchasePurchaseId=" + purchasePurchaseId + ", cuponCuponId=" + cuponCuponId + '}';
    }
}

package bo.com.knowix.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.com.knowix.entity.PurchaseCuponEntity;

public interface PurchaseCuponDAO extends JpaRepository<PurchaseCuponEntity, Integer> {
    List<PurchaseCuponEntity> findByPurchasePurchaseId(Integer purchasePurchaseId);
    List<PurchaseCuponEntity> findByCuponCuponId(Integer cuponCuponId);
}

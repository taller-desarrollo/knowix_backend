package bo.com.knowix.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.com.knowix.entity.PurchaseCuponEntity;

public interface PurchaseCuponDAO extends JpaRepository<PurchaseCuponEntity, Integer> {
    PurchaseCuponEntity findById(int id);
}
package bo.com.knowix.dao;

import bo.com.knowix.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PurchaseDAO extends JpaRepository<PurchaseEntity, Integer> {
    List<PurchaseEntity> findByKcUserKcUuid(String kcUserKcUuid);
}

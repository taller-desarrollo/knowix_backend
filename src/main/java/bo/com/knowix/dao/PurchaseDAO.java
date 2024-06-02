package bo.com.knowix.dao;

import bo.com.knowix.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseDAO extends JpaRepository<PurchaseEntity, Integer> {
    List<PurchaseEntity> findByKcUserKcUuid(String kcUserKcUuid);

    @Query(
        "SELECT p FROM PurchaseEntity p WHERE p.course.kcUserKcUuid = :kcUserKcUuid AND p.datePurchase BETWEEN :startDate AND :endDate"
    )
    List<PurchaseEntity> findPurchasesBetweenDates(String kcUserKcUuid, Timestamp startDate, Timestamp endDate);
}

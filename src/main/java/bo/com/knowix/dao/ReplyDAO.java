package bo.com.knowix.dao;

import bo.com.knowix.entity.PurchaseEntity;
import bo.com.knowix.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface ReplyDAO extends JpaRepository<ReplyEntity, Integer> {

    ReplyEntity findById (int id);

    @Query(
            "SELECT c.courseName, COUNT(r.replyId), SUM(p.amount) " +
                    "FROM ReplyEntity r " +
                    "JOIN r.purchase p " +
                    "JOIN p.course c " +
                    "WHERE c.kcUserKcUuid = :kcUserKcUuid AND r.status = true " +
                    "AND p.datePurchase BETWEEN :startDate AND :endDate " +
                    "GROUP BY c.courseName " +
                    "ORDER BY SUM(p.amount) DESC"
    )
    List<Object[]> findByStatusIsTrueAndDateBetween(
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate,
            @Param("kcUserKcUuid") String kcUserKcUuid
    );

    /*@Query(
            "SELECT c.courseName, COUNT(r.replyId), SUM(p.amount) FROM ReplyEntity r, PurchaseEntity p, CourseEntity c " +
                    "WHERE r.purchase.purchaseId = p.purchaseId AND p.course.courseId = c.courseId " +
                    "AND c.kcUserKcUuid = :kcUserKcUuid AND r.status = true AND p.datePurchase " +
                    "BETWEEN :startDate AND :endDate GROUP BY c.courseName ORDER BY COUNT(r.replyId) DESC"
    )
    List<Object[]> findByStatusIsTrueAndDateBetween(
           Timestamp startDate,
           Timestamp endDate,
           String kcUserKcUuid
    );*/


    ReplyEntity findByPurchaseAndStatusIsTrue(PurchaseEntity purchaseEntity);
}
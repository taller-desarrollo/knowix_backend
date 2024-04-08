package bo.com.knowix.dao;

import bo.com.knowix.entity.PaymentMethodEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentMethodDAO extends JpaRepository<PaymentMethodEntity, Integer> {
    List<PaymentMethodEntity> findByKcUserKcUuid(String kcUserKcUuid);
}

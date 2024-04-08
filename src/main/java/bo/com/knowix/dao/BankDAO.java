package bo.com.knowix.dao;

import bo.com.knowix.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BankDAO extends JpaRepository<BankEntity, Integer> {
    List<BankEntity> findByBankNameIgnoreCaseContaining(String bankName);
}


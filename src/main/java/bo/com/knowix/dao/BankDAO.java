package bo.com.knowix.dao;

import bo.com.knowix.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankDAO extends JpaRepository<BankEntity, Integer> {
    Optional<BankEntity> findByBankName(String bankName);
}

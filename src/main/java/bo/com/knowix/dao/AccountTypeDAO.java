package bo.com.knowix.dao;

import bo.com.knowix.entity.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountTypeDAO extends JpaRepository<AccountTypeEntity, Integer> {
    Optional<AccountTypeEntity> findByDescription(String description);
}

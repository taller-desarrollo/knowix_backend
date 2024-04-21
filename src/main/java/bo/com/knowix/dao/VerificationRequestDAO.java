package bo.com.knowix.dao;

import bo.com.knowix.entity.VerificationRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRequestDAO extends JpaRepository<VerificationRequestEntity, Long> {
}

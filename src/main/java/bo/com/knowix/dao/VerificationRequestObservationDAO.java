package bo.com.knowix.dao;

import bo.com.knowix.entity.VerificationRequestEntity;
import bo.com.knowix.entity.VerificationRequestObservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRequestObservationDAO extends JpaRepository<VerificationRequestObservationEntity, Long> {
    List<VerificationRequestObservationEntity> findByVerificationRequest(VerificationRequestEntity verificationRequestEntity);
}

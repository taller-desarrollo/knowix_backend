package bo.com.knowix.dao;

import bo.com.knowix.entity.VerificationRequestAttachmentEntity;
import bo.com.knowix.entity.VerificationRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRequestAttachmentDAO extends JpaRepository<VerificationRequestAttachmentEntity, Long> {
    List<VerificationRequestAttachmentEntity> findByVerificationRequest(VerificationRequestEntity verificationRequestEntity);

}

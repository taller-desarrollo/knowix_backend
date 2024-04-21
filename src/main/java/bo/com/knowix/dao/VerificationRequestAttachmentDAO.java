package bo.com.knowix.dao;

import bo.com.knowix.entity.VerificationRequestAttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRequestAttachmentDAO extends JpaRepository<VerificationRequestAttachmentEntity, Long> {
}

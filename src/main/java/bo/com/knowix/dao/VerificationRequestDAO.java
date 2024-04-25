package bo.com.knowix.dao;

import bo.com.knowix.entity.KcUserEntity;
import bo.com.knowix.entity.VerificationRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRequestDAO extends JpaRepository<VerificationRequestEntity, Long> {

Page<VerificationRequestEntity> findAllByStatusIsTrue(Pageable pageable);
List<VerificationRequestEntity> findByKcUserEntityAndState(KcUserEntity kcUser, String state);

List<VerificationRequestEntity> findByKcUserEntity(KcUserEntity kcUser);

}

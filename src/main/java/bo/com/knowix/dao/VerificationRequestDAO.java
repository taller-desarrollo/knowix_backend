package bo.com.knowix.dao;

import bo.com.knowix.entity.KcUserEntity;
import bo.com.knowix.entity.VerificationRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRequestDAO extends JpaRepository<VerificationRequestEntity, Long> {

List<VerificationRequestEntity> findAllByStatusIsTrue();
List<VerificationRequestEntity> findByKcUserEntityAndState(KcUserEntity kcUser, String state);

List<VerificationRequestEntity> findByKcUserEntity(KcUserEntity kcUser);

}

package bo.com.knowix.dao.repository;

import bo.com.knowix.dao.KcUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KcUserRepository extends JpaRepository<KcUserEntity, Long> {
}

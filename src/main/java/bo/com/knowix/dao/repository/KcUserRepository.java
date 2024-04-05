package bo.com.knowix.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.com.knowix.entity.KcUserEntity;

public interface KcUserRepository extends JpaRepository<KcUserEntity, Long> {
    KcUserEntity findByKcUuid(String kcUuid);
}

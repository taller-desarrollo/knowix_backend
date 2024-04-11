package bo.com.knowix.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.com.knowix.entity.KcGroupEntity;

public interface KcGroupRepository extends JpaRepository<KcGroupEntity, Long> {

    KcGroupEntity findByKcGroupId(Long kcGroupId);

}

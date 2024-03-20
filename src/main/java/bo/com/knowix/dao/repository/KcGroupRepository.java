package bo.com.knowix.dao.repository;

import bo.com.knowix.dao.KcGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KcGroupRepository extends JpaRepository<KcGroupEntity, Long> {

    KcGroupEntity findByKcGroupId(Long kcGroupId);

}

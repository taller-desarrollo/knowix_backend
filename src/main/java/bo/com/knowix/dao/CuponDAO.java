package bo.com.knowix.dao;

import bo.com.knowix.entity.CuponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuponDAO extends JpaRepository<CuponEntity, Integer> {
    Optional<CuponEntity> findByCuponCode(String cuponCode);
}

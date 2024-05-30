package bo.com.knowix.dao;

import bo.com.knowix.entity.CuponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuponDAO extends JpaRepository<CuponEntity, Integer> {
}

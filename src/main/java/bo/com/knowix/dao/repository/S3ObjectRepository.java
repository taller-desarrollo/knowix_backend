package bo.com.knowix.dao.repository;

import bo.com.knowix.entity.S3ObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface S3ObjectRepository extends JpaRepository<S3ObjectEntity, Long>{
}

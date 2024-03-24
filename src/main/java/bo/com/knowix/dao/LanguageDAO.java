package bo.com.knowix.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.com.knowix.entity.LanguageEntity;

public interface LanguageDAO extends JpaRepository<LanguageEntity, Integer> {

}

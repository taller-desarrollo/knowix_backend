package bo.com.knowix.dao;

import bo.com.knowix.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReplyDAO extends JpaRepository<ReplyEntity, Integer> {

    ReplyEntity findById (int id);

}
package bo.com.knowix.dao;

import bo.com.knowix.entity.CommentReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReportDAO extends JpaRepository<CommentReportEntity, Integer>{
}

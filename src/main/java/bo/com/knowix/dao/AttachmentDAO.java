package bo.com.knowix.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.com.knowix.entity.AttachmentEntity;

public interface AttachmentDAO extends JpaRepository<AttachmentEntity, Integer>{
    
    List<AttachmentEntity> findByContentId(Integer contentId);

}

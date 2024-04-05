package bo.com.knowix.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.com.knowix.entity.SocialMediaEntity;

public interface SocialMediaDAO extends JpaRepository<SocialMediaEntity, Integer> {
    
}

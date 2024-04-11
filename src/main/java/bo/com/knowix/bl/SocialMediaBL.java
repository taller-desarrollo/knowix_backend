package bo.com.knowix.bl;

import bo.com.knowix.dao.SocialMediaDAO;
import bo.com.knowix.dto.SocialMediaDTO;
import bo.com.knowix.entity.SocialMediaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocialMediaBL {

    private final SocialMediaDAO socialMediaDAO;

    @Autowired
    public SocialMediaBL(SocialMediaDAO socialMediaDAO) {
        this.socialMediaDAO = socialMediaDAO;
    }

    // Method to fetch all social media links by user UUID
    public List<SocialMediaDTO> getSocialMediaLinksByUserUuid(String kcUserUuid) {
        List<SocialMediaEntity> socialMediaEntities = socialMediaDAO.findByKcUserUuid(kcUserUuid);
        return socialMediaEntities.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    // Method to update social media link
    public SocialMediaDTO updateSocialMediaLink(SocialMediaDTO socialMediaDTO) {
        SocialMediaEntity socialMediaEntity = socialMediaDAO.findById(socialMediaDTO.getSocialMediaId())
                .orElseThrow(() -> new RuntimeException(
                        "Social media link not found with id: " + socialMediaDTO.getSocialMediaId()));
        socialMediaEntity.setSocialMediaUrl(socialMediaDTO.getSocialMediaUrl());
        socialMediaEntity.setStatus(socialMediaDTO.isStatus());
        socialMediaEntity = socialMediaDAO.save(socialMediaEntity);
        return convertEntityToDTO(socialMediaEntity);
    }

    // Method to save social media link
    public SocialMediaDTO saveSocialMediaLink(SocialMediaDTO socialMediaDTO) {
        SocialMediaEntity socialMediaEntity = convertDTOToEntity(socialMediaDTO);
        socialMediaEntity = socialMediaDAO.save(socialMediaEntity);
        return convertEntityToDTO(socialMediaEntity);
    }

    // Helper method to convert Entity to DTO
    private SocialMediaDTO convertEntityToDTO(SocialMediaEntity socialMediaEntity) {
        SocialMediaDTO socialMediaDTO = new SocialMediaDTO();
        socialMediaDTO.setSocialMediaId(socialMediaEntity.getSocialMediaId());
        socialMediaDTO.setKcUserUuid(socialMediaEntity.getKcUserUuid());
        socialMediaDTO.setSocialMediaUrl(socialMediaEntity.getSocialMediaUrl());
        socialMediaDTO.setStatus(socialMediaEntity.getStatus());
        return socialMediaDTO;
    }

    // Helper method to convert DTO to Entity
    private SocialMediaEntity convertDTOToEntity(SocialMediaDTO socialMediaDTO) {
        SocialMediaEntity socialMediaEntity = new SocialMediaEntity();
        socialMediaEntity.setSocialMediaId(socialMediaDTO.getSocialMediaId());
        socialMediaEntity.setKcUserUuid(socialMediaDTO.getKcUserUuid());
        socialMediaEntity.setSocialMediaUrl(socialMediaDTO.getSocialMediaUrl());
        socialMediaEntity.setStatus(socialMediaDTO.isStatus());
        return socialMediaEntity;
    }
}
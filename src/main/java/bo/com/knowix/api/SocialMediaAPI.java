package bo.com.knowix.api;

import bo.com.knowix.bl.SocialMediaBL;
import bo.com.knowix.dto.SocialMediaDTO;
import bo.com.knowix.entity.SocialMediaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/social-media")
public class SocialMediaAPI {

    private final SocialMediaBL socialMediaBl;
    private static final Logger LOGGER = Logger.getLogger(SocialMediaAPI.class.getName());

    @Autowired
    public SocialMediaAPI(SocialMediaBL socialMediaBl) {
        this.socialMediaBl = socialMediaBl;
    }

    // Method to create a social media link
    @PostMapping()
    public ResponseEntity<SocialMediaEntity> createSocialMediaLink(@RequestBody SocialMediaDTO socialMediaDTO) {
        LOGGER.info("Starting process to create a social media link");
        try {
            SocialMediaDTO newSocialMedia = socialMediaBl.saveSocialMediaLink(socialMediaDTO);
            return ResponseEntity.ok(convertToEntity(newSocialMedia));
        } catch (Exception e) {
            LOGGER.warning("Error occurred while creating a social media link: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to create a social media link");
        }
    }

    // Method to fetch all social media links by user UUID
    @GetMapping("/user/{kcUserUuid}")
    public ResponseEntity<List<SocialMediaDTO>> getSocialMediaLinksByUserUuid(
            @PathVariable("kcUserUuid") String kcUserUuid) {
        LOGGER.info("Starting process to fetch social media links by user UUID: " + kcUserUuid);
        try {
            List<SocialMediaDTO> socialMediaLinks = socialMediaBl.getSocialMediaLinksByUserUuid(kcUserUuid);
            if (socialMediaLinks.isEmpty()) {
                LOGGER.info("No social media links found for user UUID: " + kcUserUuid);
                return ResponseEntity.noContent().build();
            }
            LOGGER.info(socialMediaLinks.size() + " social media links found for user UUID: " + kcUserUuid);
            return ResponseEntity.ok(socialMediaLinks);
        } catch (Exception e) {
            LOGGER.warning("Error occurred while fetching social media links by user UUID: " + kcUserUuid + " - "
                    + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to fetch social media links by user UUID: " + kcUserUuid);
        }
    }

    // Method to update a social media link
    @PutMapping("/{socialMediaId}")
    public ResponseEntity<SocialMediaDTO> updateSocialMediaLink(@PathVariable("socialMediaId") Integer socialMediaId,
            @RequestBody SocialMediaDTO socialMediaDTO) {
        LOGGER.info("Starting process to update a social media link with ID: " + socialMediaId);
        try {
            socialMediaDTO.setSocialMediaId(socialMediaId);
            SocialMediaDTO updatedSocialMediaDTO = socialMediaBl.updateSocialMediaLink(socialMediaDTO);
            if (updatedSocialMediaDTO != null) {
                return ResponseEntity.ok(updatedSocialMediaDTO);
            } else {
                LOGGER.info("No social media link found with ID: " + socialMediaId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            LOGGER.warning("Error occurred while updating a social media link with ID: " + socialMediaId + " - "
                    + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to update a social media link with ID: " + socialMediaId);
        }
    }

    // Helper method to convert DTO to Entity
    private SocialMediaEntity convertToEntity(SocialMediaDTO socialMediaDTO) {
        SocialMediaEntity entity = new SocialMediaEntity();
        entity.setSocialMediaId(socialMediaDTO.getSocialMediaId());
        entity.setKcUserUuid(socialMediaDTO.getKcUserUuid());
        entity.setSocialMediaUrl(socialMediaDTO.getSocialMediaUrl());
        entity.setStatus(socialMediaDTO.isStatus());
        return entity;
    }

}
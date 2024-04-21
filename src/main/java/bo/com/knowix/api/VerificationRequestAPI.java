package bo.com.knowix.api;

import bo.com.knowix.bl.MinioBL;
import bo.com.knowix.bl.VerificationRequestBL;
import bo.com.knowix.dto.VerificationRequestAttachmentDTO;
import bo.com.knowix.dto.VerificationRequestDTO;
import bo.com.knowix.dto.VerificationRequestObservationDTO;
import bo.com.knowix.entity.VerificationRequestAttachmentEntity;
import bo.com.knowix.entity.VerificationRequestEntity;
import bo.com.knowix.entity.VerificationRequestObservationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.PUT;

@RestController
@RequestMapping("/api/v1/verification-request")
public class VerificationRequestAPI {

    @Autowired
    private VerificationRequestBL verificationRequestBL;

    private Logger logger = LoggerFactory.getLogger(VerificationRequestAPI.class);

    public VerificationRequestAPI(VerificationRequestBL verificationRequestBL) {
        this.verificationRequestBL = verificationRequestBL;
    }

    @PostMapping()
    public VerificationRequestEntity createVerificationRequest(
            @RequestHeader("X-UUID") String kcUserUUID
            ) {
        logger.info("Creating verification request");
        return verificationRequestBL.createVerificationRequest(kcUserUUID);
    }

    @PutMapping("/{id}")
    public VerificationRequestEntity updateVerificationRequest(
            @PathVariable("id") Long id,
            @RequestBody VerificationRequestDTO verificationRequestDTO,
            @RequestHeader("X-UUID") String kcUserUUID
            ) {
        logger.info("Updating verification request");
        return verificationRequestBL.updateVerificationRequest(id, verificationRequestDTO, kcUserUUID);
    }

    @PostMapping(path= "/{id}/attachment", consumes = "multipart/form-data")
    public VerificationRequestAttachmentDTO addAttachment(
            @PathVariable("id") Long id,
            @RequestHeader("X-UUID") String kcUserUUID,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("schoolOrInstitution") String schoolOrInstitution
            ) {
        logger.info("Adding attachment to verification request");
        String bucketName = "verification-request-attachment";
        return verificationRequestBL.addAttachment(id, kcUserUUID, file, bucketName, title, description, schoolOrInstitution);
    }

    @PostMapping("/{id}/observation")
    public VerificationRequestObservationEntity addObservation(
            @PathVariable("id") Long id,
            @RequestBody VerificationRequestObservationDTO verificationRequestObservationDTO
            ) {
        logger.info("Adding observation to verification request");
        return verificationRequestBL.addObservation(id, verificationRequestObservationDTO);
    }
}

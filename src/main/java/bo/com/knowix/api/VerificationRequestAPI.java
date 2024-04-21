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
import java.util.List;

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
    public VerificationRequestDTO createVerificationRequest(
            @RequestHeader("X-UUID") String kcUserUUID
            ) {
        logger.info("Creating verification request");
        return verificationRequestBL.createVerificationRequest(kcUserUUID);
    }

    @PutMapping("/{id}")
    public VerificationRequestDTO updateVerificationRequest(
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
    public VerificationRequestObservationDTO addObservation(
            @PathVariable("id") Long id,
            @RequestBody VerificationRequestObservationDTO verificationRequestObservationDTO
            ) {
        logger.info("Adding observation to verification request");
        return verificationRequestBL.addObservation(id, verificationRequestObservationDTO);
    }

    @PutMapping("/{id}/approve")
    public VerificationRequestDTO approveVerificationRequest(
            @PathVariable("id") Long id
            ) {
        logger.info("Approving verification request");
        return verificationRequestBL.approveVerificationRequest(id);
    }

    @PutMapping("/{id}/reject")
    public VerificationRequestDTO rejectVerificationRequest(
            @PathVariable("id") Long id
            ) {
        logger.info("Rejecting verification request");
        return verificationRequestBL.rejectVerificationRequest(id);
    }

    @PutMapping("/{id}/pending")
    public VerificationRequestDTO pendingVerificationRequest(
            @PathVariable("id") Long id
            ) {
        logger.info("Pending verification request");
        return verificationRequestBL.pendingVerificationRequest(id);
    }

    @DeleteMapping("/{id}")
    public String deleteVerificationRequest(
            @PathVariable("id") Long id
            ) {
        logger.info("Deleting verification request");
        verificationRequestBL.deleteVerificationRequest(id);
        return "Verification request deleted successfully";
    }

    @GetMapping("/educator")
    public List<VerificationRequestDTO> getVerificationRequest(
            @RequestHeader("X-UUID") String kcUserUUID
            ) {
        logger.info("Getting verification request");
        return verificationRequestBL.getVerificationRequestEducator(kcUserUUID);
    }

    @GetMapping()
    public List<VerificationRequestDTO> getAllVerificationRequest() {
        logger.info("Getting verification request");
        return verificationRequestBL.getVerificationRequest();
    }
}

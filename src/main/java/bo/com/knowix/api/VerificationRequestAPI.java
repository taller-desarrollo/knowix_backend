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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<VerificationRequestDTO> createVerificationRequest(
            @RequestHeader("X-UUID") String kcUserUUID
            ) {
        logger.info("Creating verification request");
        VerificationRequestDTO verificationRequestDTO = verificationRequestBL.createVerificationRequest(kcUserUUID);
        return ResponseEntity.ok(verificationRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VerificationRequestDTO> updateVerificationRequest(
            @PathVariable("id") Long id,
            @RequestBody VerificationRequestDTO verificationRequestDTO,
            @RequestHeader("X-UUID") String kcUserUUID
            ) {
        logger.info("Updating verification request");
        VerificationRequestDTO updated = verificationRequestBL.updateVerificationRequest(id, verificationRequestDTO, kcUserUUID);
        return ResponseEntity.ok(updated);
    }

    @PostMapping(path= "/{id}/attachment", consumes = "multipart/form-data")
    public ResponseEntity<VerificationRequestAttachmentDTO> addAttachment(
            @PathVariable("id") Long id,
            @RequestHeader("X-UUID") String kcUserUUID,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("schoolOrInstitution") String schoolOrInstitution
            ) {
        logger.info("Adding attachment to verification request");
        String bucketName = "verification-request-attachment";
        VerificationRequestAttachmentDTO verificationRequestAttachmentDTO = verificationRequestBL.addAttachment(id, kcUserUUID, file, bucketName, title, description, schoolOrInstitution);
        return ResponseEntity.ok(verificationRequestAttachmentDTO);
    }

    @PostMapping("/{id}/observation")
    public ResponseEntity<VerificationRequestObservationDTO> addObservation(
            @PathVariable("id") Long id,
            @RequestBody VerificationRequestObservationDTO verificationRequestObservationDTO
            ) {
        logger.info("Adding observation to verification request");
        VerificationRequestObservationDTO observation = verificationRequestBL.addObservation(id, verificationRequestObservationDTO);
        return ResponseEntity.ok(observation);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<VerificationRequestDTO> approveVerificationRequest(
            @PathVariable("id") Long id
            ) {
        logger.info("Approving verification request");
        VerificationRequestDTO approved = verificationRequestBL.approveVerificationRequest(id);
        return ResponseEntity.ok(approved);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<VerificationRequestDTO> rejectVerificationRequest(
            @PathVariable("id") Long id
            ) {
        logger.info("Rejecting verification request");
        VerificationRequestDTO rejected = verificationRequestBL.rejectVerificationRequest(id);
        return ResponseEntity.ok(rejected);
    }

    @PutMapping("/{id}/pending")
    public ResponseEntity<VerificationRequestDTO> pendingVerificationRequest(
            @PathVariable("id") Long id
            ) {
        logger.info("Pending verification request");
        VerificationRequestDTO pending = verificationRequestBL.pendingVerificationRequest(id);
        return ResponseEntity.ok(pending);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVerificationRequest(
            @PathVariable("id") Long id
            ) {
        logger.info("Deleting verification request");
        verificationRequestBL.deleteVerificationRequest(id);
        return ResponseEntity.ok("Verification request deleted");
    }

    @GetMapping("/educator")
    public ResponseEntity<List<VerificationRequestDTO>> getVerificationRequest(
            @RequestHeader("X-UUID") String kcUserUUID
            ) {
        logger.info("Getting verification request");
        return ResponseEntity.ok(verificationRequestBL.getVerificationRequestEducator(kcUserUUID));
    }

    @GetMapping()
    public ResponseEntity<Page<VerificationRequestDTO>> getAllVerificationRequest() {
        logger.info("Getting verification request");
        return ResponseEntity.ok(verificationRequestBL.getVerificationRequest());
    }
}

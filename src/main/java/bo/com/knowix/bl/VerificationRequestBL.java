package bo.com.knowix.bl;

import bo.com.knowix.dao.VerificationRequestAttachmentDAO;
import bo.com.knowix.dao.VerificationRequestDAO;
import bo.com.knowix.dao.VerificationRequestObservationDAO;
import bo.com.knowix.dao.repository.KcUserRepository;
import bo.com.knowix.dto.S3ObjectDTO;
import bo.com.knowix.dto.VerificationRequestAttachmentDTO;
import bo.com.knowix.dto.VerificationRequestDTO;
import bo.com.knowix.dto.VerificationRequestObservationDTO;
import bo.com.knowix.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class VerificationRequestBL {

    @Autowired
    private MinioBL minioBL;
    @Autowired
    private VerificationRequestDAO verificationRequestDAO;
    @Autowired
    private VerificationRequestAttachmentDAO verificationRequestAttachmentDAO;
    @Autowired
    private VerificationRequestObservationDAO verificationRequestObservationDAO;
    @Autowired
    private KcUserRepository kcUserRepository;

    public VerificationRequestBL(MinioBL minioBL, VerificationRequestDAO verificationRequestDAO, VerificationRequestAttachmentDAO verificationRequestAttachmentDAO, VerificationRequestObservationDAO verificationRequestObservationDAO) {
        this.minioBL = minioBL;
        this.verificationRequestDAO = verificationRequestDAO;
        this.verificationRequestAttachmentDAO = verificationRequestAttachmentDAO;
        this.verificationRequestObservationDAO = verificationRequestObservationDAO;
    }

    public VerificationRequestEntity createVerificationRequest(String kcUserUUID) {

        KcUserEntity kcUser = kcUserRepository.findByKcUuid(kcUserUUID);
        if(kcUser == null) {
            throw new RuntimeException("User not found");
        }

        VerificationRequestEntity verificationRequest = new VerificationRequestEntity();
        verificationRequest.setRequestDate(new Date());
        verificationRequest.setStatus(true);
        verificationRequest.setState("PENDING");
        verificationRequest.setAdditionalComment("");
        verificationRequest.setKcUserEntity(kcUser);

        return verificationRequestDAO.save(verificationRequest);
    }

    public VerificationRequestEntity updateVerificationRequest(
            Long id,
            VerificationRequestDTO verificationRequestDTO,
            String kcUserUUID ) {
        VerificationRequestEntity verificationRequest = verificationRequestDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));

        if (!verificationRequest.getKcUserEntity().getKcUuid().equals(kcUserUUID)) {
            throw new RuntimeException("User not allowed to update this verification request");
        }

        verificationRequest.setAdditionalComment(verificationRequestDTO.getAdditionalComment());
        verificationRequest.setStatus(verificationRequestDTO.isStatus());
        return verificationRequestDAO.save(verificationRequest);
    }

    public VerificationRequestAttachmentDTO addAttachment(
            Long id,
            String kcUserUUID,
            MultipartFile file,
            String bucketName,
            String title,
            String description,
            String schoolOrInstitution) {
        VerificationRequestEntity verificationRequest = verificationRequestDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));

        if (!verificationRequest.getKcUserEntity().getKcUuid().equals(kcUserUUID)) {
            throw new RuntimeException("User not allowed to add attachment to this verification request");
        }

        S3ObjectEntity s3Object = minioBL.uploadFile(file, bucketName);
        VerificationRequestAttachmentEntity verificationRequestAttachment = new VerificationRequestAttachmentEntity();
        verificationRequestAttachment.setS3ObjectId(s3Object.getS3ObjectId().intValue());
        verificationRequestAttachment.setTitle(title);
        verificationRequestAttachment.setDescription(description);
        verificationRequestAttachment.setSchoolOrInstitution(schoolOrInstitution);
        verificationRequestAttachment.setVerificationRequest(verificationRequest);

        VerificationRequestAttachmentEntity verificationRequestAttachmentEntity = verificationRequestAttachmentDAO.save(verificationRequestAttachment);
        S3ObjectDTO s3ObjectDTO = new S3ObjectDTO();
        s3ObjectDTO.setFilename(s3Object.getFilename());
        s3ObjectDTO.setId(s3Object.getS3ObjectId());
        s3ObjectDTO.setType(s3Object.getContentType());
        String url = minioBL.getFile(bucketName, s3Object.getFilename());
        s3ObjectDTO.setUrl(url);
        return new VerificationRequestAttachmentDTO(
                verificationRequestAttachmentEntity.getId(),
                s3ObjectDTO,
                verificationRequestAttachmentEntity.getTitle(),
                verificationRequestAttachmentEntity.getDescription(),
                verificationRequestAttachmentEntity.getSchoolOrInstitution()
        );
    }

    public VerificationRequestObservationEntity addObservation(
            Long id,
            VerificationRequestObservationDTO verificationRequestObservationDTO) {
        VerificationRequestEntity verificationRequest = verificationRequestDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));

        VerificationRequestObservationEntity verificationRequestObservation = new VerificationRequestObservationEntity();
        verificationRequestObservation.setContent(verificationRequestObservationDTO.getContent());
        verificationRequestObservation.setTitle(verificationRequestObservationDTO.getTitle());
        verificationRequestObservation.setVerificationRequest(verificationRequest);

        return verificationRequestObservationDAO.save(verificationRequestObservation);
    }

}

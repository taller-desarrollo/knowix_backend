package bo.com.knowix.bl;

import bo.com.knowix.dao.VerificationRequestAttachmentDAO;
import bo.com.knowix.dao.VerificationRequestDAO;
import bo.com.knowix.dao.VerificationRequestObservationDAO;
import bo.com.knowix.dao.repository.KcUserRepository;
import bo.com.knowix.dao.repository.S3ObjectRepository;
import bo.com.knowix.dto.*;
import bo.com.knowix.entity.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    private final S3ObjectRepository s3ObjectRepository;

    public VerificationRequestBL(MinioBL minioBL, VerificationRequestDAO verificationRequestDAO, VerificationRequestAttachmentDAO verificationRequestAttachmentDAO, VerificationRequestObservationDAO verificationRequestObservationDAO,
                                 S3ObjectRepository s3ObjectRepository) {
        this.minioBL = minioBL;
        this.verificationRequestDAO = verificationRequestDAO;
        this.verificationRequestAttachmentDAO = verificationRequestAttachmentDAO;
        this.verificationRequestObservationDAO = verificationRequestObservationDAO;
        this.s3ObjectRepository = s3ObjectRepository;
    }

    public VerificationRequestDTO createVerificationRequest(String kcUserUUID) {

        //check if the user exists
        KcUserEntity kcUser = kcUserRepository.findByKcUuid(kcUserUUID);
        if(kcUser == null) {
            throw new RuntimeException("User not found");
        }

        //an educator only can have one verification request at a time
        //only can create a new verification request if the previous one is not pending or rejected
        List<VerificationRequestEntity> verificationRequestEntities = verificationRequestDAO.findByKcUserEntityAndState(kcUser, "PENDING");
        if(!verificationRequestEntities.isEmpty()){
            throw new RuntimeException("User already has a verification request pending");
        }

        //if the user has a verification request approved, then can't create a new one
        verificationRequestEntities = verificationRequestDAO.findByKcUserEntityAndState(kcUser, "APPROVED");
        if(!verificationRequestEntities.isEmpty()) {
            throw new RuntimeException("User already has a verification request approved");
        }

        //if the user has a verification request rejected, then can create a new one
        VerificationRequestEntity verificationRequest = new VerificationRequestEntity();
        verificationRequest.setRequestDate(new Date());
        verificationRequest.setStatus(true);
        verificationRequest.setState("PENDING");
        verificationRequest.setAdditionalComment("");
        verificationRequest.setKcUserEntity(kcUser);

        VerificationRequestEntity verificationRequestEntity = verificationRequestDAO.save(verificationRequest);
        VerificationRequestDTO verificationRequestDTO = new VerificationRequestDTO();
        verificationRequestDTO.setRequestDate(verificationRequestEntity.getRequestDate().toString());
        verificationRequestDTO.setStatus(verificationRequestEntity.isStatus());
        verificationRequestDTO.setState(verificationRequestEntity.getState());
        verificationRequestDTO.setAdditionalComment(verificationRequestEntity.getAdditionalComment());
        verificationRequestDTO.setId(verificationRequestEntity.getId());

        return verificationRequestDTO;
    }

    public VerificationRequestDTO updateVerificationRequest(
            Long id,
            VerificationRequestDTO verificationRequestDTO,
            String kcUserUUID ) {
        VerificationRequestEntity verificationRequest = verificationRequestDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));

        //if the user is not the owner of the verification request, then can't update it
        if (!verificationRequest.getKcUserEntity().getKcUuid().equals(kcUserUUID)) {
            throw new RuntimeException("User not allowed to update this verification request");
        }

        //if the verification request is already approved, then can't update it
        if(verificationRequest.getState().equals("APPROVED")) {
            throw new RuntimeException("Verification request already approved");
        }

        verificationRequest.setAdditionalComment(verificationRequestDTO.getAdditionalComment());

        VerificationRequestEntity verificationRequestEntity = verificationRequestDAO.save(verificationRequest);
        VerificationRequestDTO savedVerificationRequestDTO = new VerificationRequestDTO();
        savedVerificationRequestDTO.setId(verificationRequestEntity.getId());
        savedVerificationRequestDTO.setAdditionalComment(verificationRequestEntity.getAdditionalComment());
        savedVerificationRequestDTO.setState(verificationRequestEntity.getState());
        savedVerificationRequestDTO.setStatus(verificationRequestEntity.isStatus());
        savedVerificationRequestDTO.setRequestDate(verificationRequestEntity.getRequestDate().toString());
        return savedVerificationRequestDTO;

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

    public VerificationRequestObservationDTO addObservation(
            Long id,
            VerificationRequestObservationDTO verificationRequestObservationDTO) {
        VerificationRequestEntity verificationRequest = verificationRequestDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));

        VerificationRequestObservationEntity verificationRequestObservation = new VerificationRequestObservationEntity();
        verificationRequestObservation.setContent(verificationRequestObservationDTO.getContent());
        verificationRequestObservation.setTitle(verificationRequestObservationDTO.getTitle());
        verificationRequestObservation.setVerificationRequest(verificationRequest);

        VerificationRequestObservationEntity verificationRequestObservationEntity = verificationRequestObservationDAO.save(verificationRequestObservation);
        return new VerificationRequestObservationDTO(
                verificationRequestObservationEntity.getId(),
                verificationRequestObservationEntity.getContent(),
                verificationRequestObservationEntity.getTitle()
        );
    }

    public List<VerificationRequestDTO> getVerificationRequestEducator(String kcUserUUID) {
        KcUserEntity kcUser = kcUserRepository.findByKcUuid(kcUserUUID);
        if(kcUser == null) {
            throw new RuntimeException("User not found");
        }
        List<VerificationRequestEntity> verificationRequestEntities = verificationRequestDAO.findByKcUserEntity(kcUser);
        List<VerificationRequestDTO> verificationRequestDTOS = new ArrayList<>();
        for(VerificationRequestEntity verificationRequestEntity : verificationRequestEntities) {
            VerificationRequestDTO verificationRequestDTO = new VerificationRequestDTO();
            verificationRequestDTO.setId(verificationRequestEntity.getId());
            verificationRequestDTO.setAdditionalComment(verificationRequestEntity.getAdditionalComment());
            verificationRequestDTO.setRequestDate(verificationRequestEntity.getRequestDate().toString());
            verificationRequestDTO.setState(verificationRequestEntity.getState());
            verificationRequestDTO.setStatus(verificationRequestEntity.isStatus());
            
            List<VerificationRequestAttachmentEntity> verificationRequestAttachmentEntities = verificationRequestAttachmentDAO.findByVerificationRequest(verificationRequestEntity);
            List<VerificationRequestAttachmentDTO> verificationRequestAttachmentDTOS = new ArrayList<>();
            for(VerificationRequestAttachmentEntity verificationRequestAttachmentEntity : verificationRequestAttachmentEntities) {
                Optional<S3ObjectEntity> s3RecoveredObject = s3ObjectRepository.findById(
                        (long)verificationRequestAttachmentEntity.getS3ObjectId()
                );
                String url = minioBL.getFile("verification-request-attachment", s3RecoveredObject.get().getFilename());
                VerificationRequestAttachmentDTO verificationRequestAttachmentDTO = getVerificationRequestAttachmentDTO(verificationRequestAttachmentEntity, s3RecoveredObject, url);
                verificationRequestAttachmentDTOS.add(verificationRequestAttachmentDTO);
            }
            verificationRequestDTO.setVerificationRequestAttachmentDTOList(verificationRequestAttachmentDTOS);

            List<VerificationRequestObservationEntity> verificationRequestObservationEntities = verificationRequestObservationDAO.findByVerificationRequest(verificationRequestEntity);
            List<VerificationRequestObservationDTO> verificationRequestObservationDTOS = getVerificationRequestObservationDTOS(verificationRequestObservationEntities);
            verificationRequestDTO.setVerificationRequestObservationDTOList(verificationRequestObservationDTOS);

            verificationRequestDTOS.add(verificationRequestDTO);
        }
        return verificationRequestDTOS;
    }

    public List<VerificationRequestDTO> getVerificationRequest() {
        List<VerificationRequestEntity> verificationRequestEntities = verificationRequestDAO.findAllByStatusIsTrue();
        List<VerificationRequestDTO> verificationRequestDTOS = new ArrayList<>();
        for(VerificationRequestEntity verificationRequestEntity : verificationRequestEntities) {
            VerificationRequestDTO verificationRequestDTO = new VerificationRequestDTO();
            verificationRequestDTO.setId(verificationRequestEntity.getId());
            verificationRequestDTO.setAdditionalComment(verificationRequestEntity.getAdditionalComment());
            verificationRequestDTO.setRequestDate(verificationRequestEntity.getRequestDate().toString());
            verificationRequestDTO.setState(verificationRequestEntity.getState());
            verificationRequestDTO.setStatus(verificationRequestEntity.isStatus());

            List<VerificationRequestAttachmentEntity> verificationRequestAttachmentEntities = verificationRequestAttachmentDAO.findByVerificationRequest(verificationRequestEntity);
            List<VerificationRequestAttachmentDTO> verificationRequestAttachmentDTOS = new ArrayList<>();
            for(VerificationRequestAttachmentEntity verificationRequestAttachmentEntity : verificationRequestAttachmentEntities) {
                Optional<S3ObjectEntity> s3RecoveredObject = s3ObjectRepository.findById(
                        (long)verificationRequestAttachmentEntity.getS3ObjectId()
                );
                String url = minioBL.getFile("verification-request-attachment", s3RecoveredObject.get().getFilename());
                VerificationRequestAttachmentDTO verificationRequestAttachmentDTO = getVerificationRequestAttachmentDTO(verificationRequestAttachmentEntity, s3RecoveredObject, url);
                verificationRequestAttachmentDTOS.add(verificationRequestAttachmentDTO);
            }
            verificationRequestDTO.setVerificationRequestAttachmentDTOList(verificationRequestAttachmentDTOS);

            List<VerificationRequestObservationEntity> verificationRequestObservationEntities = verificationRequestObservationDAO.findByVerificationRequest(verificationRequestEntity);
            List<VerificationRequestObservationDTO> verificationRequestObservationDTOS = getVerificationRequestObservationDTOS(verificationRequestObservationEntities);
            verificationRequestDTO.setVerificationRequestObservationDTOList(verificationRequestObservationDTOS);

            verificationRequestDTOS.add(verificationRequestDTO);
        }
        return verificationRequestDTOS;
    }

    private VerificationRequestDTO changeVerificationRequestState(Long id, String state) {
        VerificationRequestEntity verificationRequest = verificationRequestDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));
        verificationRequest.setState(state);
        VerificationRequestEntity verificationRequestEntity = verificationRequestDAO.save(verificationRequest);
        VerificationRequestDTO verificationRequestDTO = new VerificationRequestDTO();
        verificationRequestDTO.setId(verificationRequestEntity.getId());
        verificationRequestDTO.setAdditionalComment(verificationRequestEntity.getAdditionalComment());
        verificationRequestDTO.setRequestDate(verificationRequestEntity.getRequestDate().toString());
        verificationRequestDTO.setState(verificationRequestEntity.getState());
        verificationRequestDTO.setStatus(verificationRequestEntity.isStatus());
        return verificationRequestDTO;
    }

    public VerificationRequestDTO approveVerificationRequest(Long id) {
        return changeVerificationRequestState(id, "APPROVED");
    }

    public VerificationRequestDTO rejectVerificationRequest(Long id) {
        return changeVerificationRequestState(id, "REJECTED");
    }

    public VerificationRequestDTO pendingVerificationRequest(Long id) {
        return changeVerificationRequestState(id, "PENDING");
    }

    public void deleteVerificationRequest(Long id) {
        VerificationRequestEntity verificationRequest = verificationRequestDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Verification request not found"));
        verificationRequest.setStatus(false);
        verificationRequestDAO.save(verificationRequest);
    }

    @NotNull
    private static List<VerificationRequestObservationDTO> getVerificationRequestObservationDTOS(List<VerificationRequestObservationEntity> verificationRequestObservationEntities) {
        List<VerificationRequestObservationDTO> verificationRequestObservationDTOS = new ArrayList<>();
        for(VerificationRequestObservationEntity verificationRequestObservationEntity : verificationRequestObservationEntities) {
            VerificationRequestObservationDTO verificationRequestObservationDTO = new VerificationRequestObservationDTO();
            verificationRequestObservationDTO.setId(verificationRequestObservationEntity.getId());
            verificationRequestObservationDTO.setContent(verificationRequestObservationEntity.getContent());
            verificationRequestObservationDTO.setTitle(verificationRequestObservationEntity.getTitle());
            verificationRequestObservationDTOS.add(verificationRequestObservationDTO);
        }
        return verificationRequestObservationDTOS;
    }

    @NotNull
    private static VerificationRequestAttachmentDTO getVerificationRequestAttachmentDTO(VerificationRequestAttachmentEntity verificationRequestAttachmentEntity, Optional<S3ObjectEntity> s3RecoveredObject, String url) {
        S3ObjectDTO s3ObjectDTO = new S3ObjectDTO();
        s3ObjectDTO.setFilename(s3RecoveredObject.get().getFilename());
        s3ObjectDTO.setId(s3RecoveredObject.get().getS3ObjectId());
        s3ObjectDTO.setType(s3RecoveredObject.get().getContentType());
        s3ObjectDTO.setUrl(url);
        return new VerificationRequestAttachmentDTO(
                verificationRequestAttachmentEntity.getId(),
                s3ObjectDTO,
                verificationRequestAttachmentEntity.getTitle(),
                verificationRequestAttachmentEntity.getDescription(),
                verificationRequestAttachmentEntity.getSchoolOrInstitution()
        );
    }

}

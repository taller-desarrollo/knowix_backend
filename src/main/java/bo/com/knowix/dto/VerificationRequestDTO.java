package bo.com.knowix.dto;

import java.util.List;

public class VerificationRequestDTO {
    private Long id;
    private boolean status;
    private String state;
    private String additionalComment;
    private KcUserDto kcUserDto;
    private String requestDate;

    private List<VerificationRequestAttachmentDTO> verificationRequestAttachmentDTOList;
    private List<VerificationRequestObservationDTO> verificationRequestObservationDTOList;

    public VerificationRequestDTO() {
    }

    public VerificationRequestDTO(Long id, boolean status, String state, String additionalComment, KcUserDto kcUserDto, String requestDate) {
        this.id = id;
        this.status = status;
        this.state = state;
        this.additionalComment = additionalComment;
        this.kcUserDto = kcUserDto;
        this.requestDate = requestDate;
    }

    public VerificationRequestDTO(Long id, boolean status, String state, String additionalComment, KcUserDto kcUserDto, String requestDate, List<VerificationRequestAttachmentDTO> verificationRequestAttachmentDTOList, List<VerificationRequestObservationDTO> verificationRequestObservationDTOList) {
        this.id = id;
        this.status = status;
        this.state = state;
        this.additionalComment = additionalComment;
        this.kcUserDto = kcUserDto;
        this.requestDate = requestDate;
        this.verificationRequestAttachmentDTOList = verificationRequestAttachmentDTOList;
        this.verificationRequestObservationDTOList = verificationRequestObservationDTOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAdditionalComment() {
        return additionalComment;
    }

    public void setAdditionalComment(String additionalComment) {
        this.additionalComment = additionalComment;
    }

    public KcUserDto getKcUserDto() {
        return kcUserDto;
    }

    public void setKcUserDto(KcUserDto kcUserDto) {
        this.kcUserDto = kcUserDto;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public List<VerificationRequestAttachmentDTO> getVerificationRequestAttachmentDTOList() {
        return verificationRequestAttachmentDTOList;
    }

    public void setVerificationRequestAttachmentDTOList(List<VerificationRequestAttachmentDTO> verificationRequestAttachmentDTOList) {
        this.verificationRequestAttachmentDTOList = verificationRequestAttachmentDTOList;
    }

    public List<VerificationRequestObservationDTO> getVerificationRequestObservationDTOList() {
        return verificationRequestObservationDTOList;
    }

    public void setVerificationRequestObservationDTOList(List<VerificationRequestObservationDTO> verificationRequestObservationDTOList) {
        this.verificationRequestObservationDTOList = verificationRequestObservationDTOList;
    }
}

package bo.com.knowix.dto;

import java.util.List;

public class VerificationRequestDTO {
    private Long id;
    private boolean status;
    private String state;
    private String additionalComment;
    private KcUserDto kcUserDto;
    private String requestDate;

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

}

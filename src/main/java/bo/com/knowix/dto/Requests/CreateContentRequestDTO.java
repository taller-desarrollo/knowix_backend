package bo.com.knowix.dto.Requests;

import java.util.List;

import bo.com.knowix.dto.AttachmentDTO;

public class CreateContentRequestDTO {
    public Integer sectionId;
    public String contentTitle;
    public Boolean status;
    public List<AttachmentDTO> attachments;
}

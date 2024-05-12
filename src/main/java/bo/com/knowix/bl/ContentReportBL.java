package bo.com.knowix.bl;

import bo.com.knowix.dao.ContentDAO;
import bo.com.knowix.dao.ContentReportDAO;
import bo.com.knowix.dto.ContentDTO;
import bo.com.knowix.dto.ContentReportDTO;
import bo.com.knowix.entity.ContentReportEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ContentReportBL {

    private final ContentReportDAO contentReportDAO;

    private final ContentDAO contentDAO;

    private final Logger logger = LoggerFactory.getLogger(ContentReportBL.class);
    @Autowired
    public ContentReportBL(ContentReportDAO contentReportDAO, ContentDAO contentDAO) {
        this.contentReportDAO = contentReportDAO;
        this.contentDAO = contentDAO;
    }

    public List<ContentReportDTO> getContentReports() {
        List<ContentReportEntity> contentReportEntities = contentReportDAO.findAll();
        return contentReportEntities.stream().map(this::toDTO).toList();
    }

    public ContentReportDTO getContentReportById(int contentId) {
        return toDTO(contentReportDAO.findById(contentId).orElseThrow());
    }

    public ContentReportDTO createContentReport(int contentId, String contentReportReason) {
        ContentReportEntity contentReportEntity = new ContentReportEntity();
        contentReportEntity.setContentReportReason(contentReportReason);
        contentReportEntity.setContent(contentDAO.findById(contentId).orElseThrow());
        contentReportEntity.setContentReportDate(new Timestamp(System.currentTimeMillis()));
        contentReportEntity.setStatus("PENDING");
        return toDTO(contentReportDAO.save(contentReportEntity));
    }

    public ContentReportDTO updateContentReport(int contentReportId, String status) {
        ContentReportEntity contentReportEntity = contentReportDAO.findById(contentReportId).orElseThrow();
        contentReportEntity.setStatus(status);
        /*if (status.equals("RESOLVED")) {
            logger.info("Content report resolved, deleting content");
            contentReportEntity.getContent().setStatus(false);
        }*/
        return toDTO(contentReportDAO.save(contentReportEntity));
    }

    private ContentReportDTO toDTO(ContentReportEntity contentReportEntity) {
        ContentDTO contentDTO = new ContentDTO();
        contentDTO.setContentId(contentReportEntity.getContent().getContentId());
        contentDTO.setContentTitle(contentReportEntity.getContent().getContentTitle());
        contentDTO.setStatus(contentReportEntity.getContent().getStatus());
        contentDTO.setSectionId(contentReportEntity.getContent().getSection().getSectionId());
        return new ContentReportDTO(
                contentReportEntity.getContentReportId(),
                contentReportEntity.getContentReportReason(),
                contentReportEntity.getContentReportDate(),
                contentReportEntity.getStatus(),
                contentDTO
        );
    }
}

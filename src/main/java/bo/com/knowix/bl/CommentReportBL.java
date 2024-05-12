package bo.com.knowix.bl;

import bo.com.knowix.dao.CommentDAO;
import bo.com.knowix.dao.CommentReportDAO;
import bo.com.knowix.dto.CommentDTO;
import bo.com.knowix.dto.CommentReportDTO;
import bo.com.knowix.entity.CommentEntity;
import bo.com.knowix.entity.CommentReportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CommentReportBL {
    private final CommentReportDAO commentReportDAO;
    private final CommentDAO commentDAO;

    @Autowired
    public CommentReportBL(CommentReportDAO commentReportDAO, CommentDAO commentDAO) {
        this.commentReportDAO = commentReportDAO;
        this.commentDAO = commentDAO;
    }

    public List<CommentReportDTO> getCommentReports() {
        List<CommentReportEntity> commentReportEntities = commentReportDAO.findAll();
        return commentReportEntities.stream().map(this::toDTO).toList();
    }

    public CommentReportDTO getCommentReportById(int commentReportId) {
        Optional<CommentReportEntity> commentReportEntity = commentReportDAO.findById(commentReportId);
        return commentReportEntity.map(this::toDTO).orElse(null);
    }

    public CommentReportDTO createCommentReport(int commentId, String commentReportReason) {
        CommentReportEntity commentReportEntity = new CommentReportEntity();
        commentReportEntity.setCommentReportReason(commentReportReason);
        CommentEntity commentEntity = commentDAO.findById(commentId).orElseThrow();
        commentReportEntity.setComment(commentEntity);
        commentReportEntity.setCommentReportDate(new Timestamp(System.currentTimeMillis()));
        commentReportEntity.setStatus("PENDING");
        commentReportEntity.setCommentReportReason(commentReportReason);
        return toDTO(commentReportDAO.save(commentReportEntity));
    }

    public CommentReportDTO updateCommentReport(int commentReportId, String status) {
        CommentReportEntity commentReportEntity = commentReportDAO.findById(commentReportId).orElseThrow();
        commentReportEntity.setStatus(status);
        return toDTO(commentReportDAO.save(commentReportEntity));
    }

    private CommentReportDTO toDTO(CommentReportEntity commentReportEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(commentReportEntity.getComment().getCommentId());
        commentDTO.setContent(commentReportEntity.getComment().getContent());
        commentDTO.setStatus(commentReportEntity.getComment().isStatus());
        commentDTO.setKcUserKcUuid(commentReportEntity.getComment().getKcUserKcUuid());
        return new CommentReportDTO(
            commentReportEntity.getCommentReportId(),
            commentReportEntity.getCommentReportReason(),
            commentReportEntity.getCommentReportDate(),
            commentReportEntity.getStatus(),
            commentDTO
        );
    }
}

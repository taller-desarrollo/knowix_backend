package bo.com.knowix.bl;

import bo.com.knowix.api.ContentReportAPI;
import bo.com.knowix.dao.CommentDAO;
import bo.com.knowix.dto.CommentDTO;
import bo.com.knowix.entity.CommentEntity;
import bo.com.knowix.entity.CourseEntity;
import bo.com.knowix.entity.KcUserEntity;
import bo.com.knowix.service.EmailService;
import bo.com.knowix.dao.CourseDAO;
import bo.com.knowix.dao.repository.KcUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CommentBL {

    private final CommentDAO commentDAO;
    private final CourseDAO courseDAO;

    private static final Logger LOGGER = Logger.getLogger(ContentReportAPI.class.getName());

    @Autowired
    private EmailService emailService;
    @Autowired
    private KcUserRepository kcUserRepository;

    @Autowired
    public CommentBL(CommentDAO commentDAO, CourseDAO courseDAO, EmailService emailService, KcUserRepository kcUserRepository) {
        this.commentDAO = commentDAO;
        this.courseDAO = courseDAO;
        this.emailService = emailService;
        this.kcUserRepository = kcUserRepository;
    }

    @Transactional
    public CommentDTO createParentComment(int courseId, CommentDTO commentDTO) {
        CourseEntity courseEntity = courseDAO.findById(courseId).orElseThrow();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentDTO.getContent());
        commentEntity.setCreationDate(commentDTO.getCreationDate() != null ? commentDTO.getCreationDate() : new Timestamp(System.currentTimeMillis()));
        commentEntity.setStatus(commentDTO.isStatus());
        commentEntity.setCourse(courseEntity);
        commentEntity.setKcUserKcUuid(commentDTO.getKcUserKcUuid());
        commentEntity.setParentComment(null);

        commentDAO.save(commentEntity);

        commentDTO.setCommentId(commentEntity.getCommentId());

        return commentDTO;
    }

    public List<CommentDTO> getCommentsByCourseId(int courseId) {
        List<CommentEntity> commentEntities = commentDAO.findByCourseCourseIdAndStatusIsTrue(courseId);
        return commentEntities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    public CommentDTO createChildComment(int parentCommentId, CommentDTO commentDTO) {
        CommentEntity parentCommentEntity = commentDAO.findById(parentCommentId).orElseThrow();
        CourseEntity courseEntity = parentCommentEntity.getCourse();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentDTO.getContent());
        commentEntity.setCreationDate(commentDTO.getCreationDate() != null ? commentDTO.getCreationDate() : new Timestamp(System.currentTimeMillis()));
        commentEntity.setStatus(commentDTO.isStatus());
        commentEntity.setCourse(courseEntity);
        commentEntity.setKcUserKcUuid(commentDTO.getKcUserKcUuid());
        commentEntity.setParentComment(parentCommentEntity);

        commentDAO.save(commentEntity);

        commentDTO.setCommentId(commentEntity.getCommentId());
        commentDTO.setParentCommentId(parentCommentId);

        LOGGER.info("Finding user by id: " + parentCommentEntity.getKcUserKcUuid());
        KcUserEntity kcUser = kcUserRepository.findByKcUuid(parentCommentEntity.getKcUserKcUuid());
        KcUserEntity kcUserComment = kcUserRepository.findByKcUuid(commentDTO.getKcUserKcUuid());
        if(kcUser == null) {
            LOGGER.info("User not found");
            throw new RuntimeException("User not found");
        }
        else {
            String title = "Tu comentario ha sido respondido";
            LOGGER.info("User found, sending email to: " + kcUser.getEmail());
            emailService.sendEmail(kcUser.getEmail(), title, 
                "Usuario: " + kcUserComment.getFirstName() + " " + kcUserComment.getLastName() + "\n" +
                "Respuesta: " + commentDTO.getContent());
        }

        return commentDTO;
    }

    public List<CommentDTO> getChildComments(int parentCommentId) {
        List<CommentEntity> commentEntities = commentDAO.findByParentCommentCommentIdAndStatusIsTrue(parentCommentId);
        return commentEntities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<CommentDTO> getParentCommentsByCourseId(int courseId) {
        List<CommentEntity> commentEntities = commentDAO.findByCourseCourseIdAndStatusIsTrue(courseId).stream()
                .filter(comment -> comment.getParentComment() == null)
                .collect(Collectors.toList());
        return commentEntities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public int countChildComments(int parentCommentId) {
        return commentDAO.findByParentCommentCommentIdAndStatusIsTrue(parentCommentId).size();
    }

    private CommentDTO convertToDTO(CommentEntity entity) {
        if (entity == null) return null;

        return new CommentDTO(
            entity.getCommentId(),
            entity.getContent(),
            entity.getCreationDate(),
            entity.isStatus(),
            entity.getCourse().getCourseId(),
            entity.getKcUserKcUuid(),
            entity.getParentComment() != null ? entity.getParentComment().getCommentId() : null
        );
    }
}

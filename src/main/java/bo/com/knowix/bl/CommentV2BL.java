package bo.com.knowix.bl;

import bo.com.knowix.api.ContentReportAPI;
import bo.com.knowix.dao.CommentDAO;
import bo.com.knowix.dao.CourseDAO;
import bo.com.knowix.dao.repository.KcUserRepository;
import bo.com.knowix.dto.CommentDTO;
import bo.com.knowix.dto.CommentUserDTO;
import bo.com.knowix.entity.CommentEntity;
import bo.com.knowix.entity.CourseEntity;
import bo.com.knowix.entity.KcUserEntity;
import bo.com.knowix.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CommentV2BL {

    private final CommentDAO commentDAO;
    private final CourseDAO courseDAO;
    private final KcUserRepository kcUserRepository;

    private static final Logger LOGGER = Logger.getLogger(ContentReportAPI.class.getName());

    @Autowired
    private EmailService emailService;

    @Autowired
    public CommentV2BL(CommentDAO commentDAO, CourseDAO courseDAO, KcUserRepository kcUserRepository, EmailService emailService) {
        this.commentDAO = commentDAO;
        this.courseDAO = courseDAO;
        this.kcUserRepository = kcUserRepository;
        this.emailService = emailService;
    }

    @Transactional
    public CommentUserDTO createParentComment(int courseId, CommentDTO commentDTO) {
        CourseEntity courseEntity = courseDAO.findById(courseId).orElseThrow();

        // Recuperar información del usuario
        KcUserEntity kcUserEntity = kcUserRepository.findByKcUuid(commentDTO.getKcUserKcUuid());
        if (kcUserEntity == null) {
            throw new RuntimeException("User not found");
        }

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentDTO.getContent());
        commentEntity.setCreationDate(commentDTO.getCreationDate() != null ? commentDTO.getCreationDate() : new Timestamp(System.currentTimeMillis()));
        commentEntity.setStatus(commentDTO.isStatus());
        commentEntity.setCourse(courseEntity);
        commentEntity.setKcUserKcUuid(commentDTO.getKcUserKcUuid());
        commentEntity.setParentComment(null);

        commentDAO.save(commentEntity);

        return new CommentUserDTO(
            commentEntity.getCommentId(),
            commentEntity.getContent(),
            commentEntity.getCreationDate(),
            commentEntity.isStatus(),
            courseId,
            commentEntity.getKcUserKcUuid(),
            null,
            kcUserEntity.getFirstName(),
            kcUserEntity.getLastName(),
            kcUserEntity.getEmail()
        );
    }

    public List<CommentUserDTO> getCommentsByCourseId(int courseId) {
        List<CommentEntity> commentEntities = commentDAO.findByCourseCourseIdAndStatusIsTrue(courseId);
        return commentEntities.stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    @Transactional
    public CommentUserDTO createChildComment(int parentCommentId, CommentDTO commentDTO) {
        CommentEntity parentCommentEntity = commentDAO.findById(parentCommentId).orElseThrow();
        CourseEntity courseEntity = parentCommentEntity.getCourse();

        // Recuperar información del usuario
        KcUserEntity kcUserEntity = kcUserRepository.findByKcUuid(commentDTO.getKcUserKcUuid());
        if (kcUserEntity == null) {
            throw new RuntimeException("User not found");
        }

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentDTO.getContent());
        commentEntity.setCreationDate(commentDTO.getCreationDate() != null ? commentDTO.getCreationDate() : new Timestamp(System.currentTimeMillis()));
        commentEntity.setStatus(commentDTO.isStatus());
        commentEntity.setCourse(courseEntity);
        commentEntity.setKcUserKcUuid(commentDTO.getKcUserKcUuid());
        commentEntity.setParentComment(parentCommentEntity);

        commentDAO.save(commentEntity);

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

        return new CommentUserDTO(
            commentEntity.getCommentId(),
            commentEntity.getContent(),
            commentEntity.getCreationDate(),
            commentEntity.isStatus(),
            courseEntity.getCourseId(),
            commentEntity.getKcUserKcUuid(),
            parentCommentId,
            kcUserEntity.getFirstName(),
            kcUserEntity.getLastName(),
            kcUserEntity.getEmail()
        );
    }


    public List<CommentUserDTO> getChildComments(int parentCommentId) {
        List<CommentEntity> commentEntities = commentDAO.findByParentCommentCommentIdAndStatusIsTrue(parentCommentId);
        return commentEntities.stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    public List<CommentUserDTO> getParentCommentsByCourseId(int courseId) {
        List<CommentEntity> commentEntities = commentDAO.findByCourseCourseIdAndStatusIsTrue(courseId).stream()
                .filter(comment -> comment.getParentComment() == null)
                .collect(Collectors.toList());
        return commentEntities.stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    public int countChildComments(int parentCommentId) {
        return commentDAO.findByParentCommentCommentIdAndStatusIsTrue(parentCommentId).size();
    }

    private CommentUserDTO convertToUserDTO(CommentEntity entity) {
        if (entity == null) return null;

        KcUserEntity userEntity = kcUserRepository.findByKcUuid(entity.getKcUserKcUuid());
        if (userEntity == null) return null;

        return new CommentUserDTO(
            entity.getCommentId(),
            entity.getContent(),
            entity.getCreationDate(),
            entity.isStatus(),
            entity.getCourse().getCourseId(),
            entity.getKcUserKcUuid(),
            entity.getParentComment() != null ? entity.getParentComment().getCommentId() : null,
            userEntity.getFirstName(),
            userEntity.getLastName(),
            userEntity.getEmail()
        );
    }
}

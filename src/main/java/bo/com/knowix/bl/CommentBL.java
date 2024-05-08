package bo.com.knowix.bl;

import bo.com.knowix.dao.CommentDAO;
import bo.com.knowix.dto.CommentDTO;
import bo.com.knowix.entity.CommentEntity;
import bo.com.knowix.entity.CourseEntity;
import bo.com.knowix.dao.CourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentBL {

    private final CommentDAO commentDAO;
    private final CourseDAO courseDAO;

    @Autowired
    public CommentBL(CommentDAO commentDAO, CourseDAO courseDAO) {
        this.commentDAO = commentDAO;
        this.courseDAO = courseDAO;
    }

    @Transactional
    public CommentDTO createParentComment(int courseId, CommentDTO commentDTO) {
        // Busca el curso en la base de datos
        CourseEntity courseEntity = courseDAO.findById(courseId).orElseThrow();
        
        // Crea una nueva entidad de comentario
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentDTO.getContent());
        commentEntity.setCreationDate(commentDTO.getCreationDate() != null ? commentDTO.getCreationDate() : new Timestamp(System.currentTimeMillis()));
        commentEntity.setStatus(commentDTO.isStatus());
        commentEntity.setCourse(courseEntity);
        commentEntity.setKcUserKcUuid(commentDTO.getKcUserKcUuid());
        commentEntity.setParentComment(null); // Comentario padre

        // Guarda el comentario en la base de datos
        commentDAO.save(commentEntity);

        // Actualiza el DTO con el ID generado
        commentDTO.setCommentId(commentEntity.getCommentId());

        return commentDTO;
    }

    public List<CommentDTO> getCommentsByCourseId(int courseId) {
        // Encuentra los comentarios asociados a un curso
        List<CommentEntity> commentEntities = commentDAO.findByCourseCourseId(courseId);

        // Convierte las entidades a DTOs
        return commentEntities.stream().map(this::convertToDTO).collect(Collectors.toList());
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

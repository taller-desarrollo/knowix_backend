package bo.com.knowix.dao;

import bo.com.knowix.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentDAO extends JpaRepository<CommentEntity, Integer> {
    // Obtener todos los comentarios por el identificador de curso
    List<CommentEntity> findByCourseCourseId(int courseId);

    // Obtener todos los comentarios realizados por un usuario específico
    List<CommentEntity> findByKcUserKcUuid(String kcUserKcUuid);

    // Obtener todos los comentarios que sean hijos de un comentario específico
    List<CommentEntity> findByParentCommentCommentId(int parentCommentId);

    // Obtener todos los comentarios principales (sin padre)
    List<CommentEntity> findByParentCommentIsNull();
}



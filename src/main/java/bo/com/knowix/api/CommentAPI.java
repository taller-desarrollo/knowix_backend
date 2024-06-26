package bo.com.knowix.api;

import bo.com.knowix.bl.CommentBL;
import bo.com.knowix.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentAPI {

    private final CommentBL commentBL;
    private static final Logger LOGGER = Logger.getLogger(CommentAPI.class.getName());

    @Autowired
    public CommentAPI(CommentBL commentBL) {
        this.commentBL = commentBL;
    }

    @PostMapping("/parent")
    public ResponseEntity<?> createParentComment(
            @RequestParam("courseId") int courseId,
            @RequestBody CommentDTO commentDTO) {
                LOGGER.info("Starting process to create parent comment");
        try {
            CommentDTO createdComment = commentBL.createParentComment(courseId, commentDTO);
            LOGGER.info("Parent comment created successfully");
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while creating parent comment: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } finally {
            LOGGER.info("Finished process to create parent comment");
        }
    }

    @PostMapping("/child")
    public ResponseEntity<?> createChildComment(
            @RequestParam("parentCommentId") int parentCommentId,
            @RequestBody CommentDTO commentDTO) {
                LOGGER.info("Starting process to create child comment");
        try {
            CommentDTO createdComment = commentBL.createChildComment(parentCommentId, commentDTO);
            LOGGER.info("Child comment created successfully");
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while creating child comment: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } finally {
            LOGGER.info("Finished process to create child comment");
        }
    }

    @GetMapping("/course/{courseId}/parents")
    public ResponseEntity<?> getParentCommentsByCourseId(@PathVariable int courseId) {
        LOGGER.info("Starting process to fetch parent comments by course ID: " + courseId);
        try {
            List<CommentDTO> comments = commentBL.getParentCommentsByCourseId(courseId);
            if (comments.isEmpty()) {
                LOGGER.info("No parent comments found for course ID: " + courseId);
                return ResponseEntity.ok("No hay comentarios padres para este curso");
            }
            LOGGER.info("Parent comments fetched successfully");
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while fetching parent comments by course ID: " + courseId + " - " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Finished process to fetch parent comments by course ID: " + courseId);
        }
    }

    @GetMapping("/parent/{parentCommentId}/children")
    public ResponseEntity<?> getChildComments(@PathVariable int parentCommentId) {
        LOGGER.info("Starting process to fetch child comments by parent comment ID: " + parentCommentId);
        try {
            List<CommentDTO> comments = commentBL.getChildComments(parentCommentId);
            if (comments.isEmpty()) {
                LOGGER.info("No child comments found for parent comment ID: " + parentCommentId);
                return ResponseEntity.ok("No hay comentarios hijos para este comentario padre");
            }
            LOGGER.info("Child comments fetched successfully");
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while fetching child comments by parent comment ID: " + parentCommentId + " - " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Finished process to fetch child comments by parent comment ID: " + parentCommentId);
        }
    }

    @GetMapping("/parent/{parentCommentId}/children/count")
    public ResponseEntity<?> countChildComments(@PathVariable int parentCommentId) {
        LOGGER.info("Starting process to count child comments by parent comment ID: " + parentCommentId);
        try {
            int count = commentBL.countChildComments(parentCommentId);
            LOGGER.info("Child comments counted successfully");
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while counting child comments by parent comment ID: " + parentCommentId + " - " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Finished process to count child comments by parent comment ID: " + parentCommentId);
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getCommentsByCourseId(@PathVariable int courseId) {
        LOGGER.info("Starting process to fetch comments by course ID: " + courseId);
        try {
            List<CommentDTO> comments = commentBL.getCommentsByCourseId(courseId);
            if (comments.isEmpty()) {
                LOGGER.info("No comments found for course ID: " + courseId);
                return ResponseEntity.ok("No hay comentarios para este curso");
            }
            LOGGER.info("Comments fetched successfully");
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while fetching comments by course ID: " + courseId + " - " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Finished process to fetch comments by course ID: " + courseId);
        }
    }
}

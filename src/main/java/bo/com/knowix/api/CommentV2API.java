package bo.com.knowix.api;

import bo.com.knowix.bl.CommentV2BL;
import bo.com.knowix.dto.CommentDTO;
import bo.com.knowix.dto.CommentUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v2/comment")
public class CommentV2API {

    private final CommentV2BL commentV2BL;
    private static final Logger LOGGER = Logger.getLogger(CommentV2API.class.getName());

    @Autowired
    public CommentV2API(CommentV2BL commentV2BL) {
        this.commentV2BL = commentV2BL;
    }

    @PostMapping("/parent")
    public ResponseEntity<?> createParentComment(
            @RequestParam("courseId") int courseId,
            @RequestBody CommentDTO commentDTO) {
        LOGGER.info("Starting process to create parent comment");
        try {
            CommentUserDTO createdComment = commentV2BL.createParentComment(courseId, commentDTO);
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
            CommentUserDTO createdComment = commentV2BL.createChildComment(parentCommentId, commentDTO);
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
            List<CommentUserDTO> comments = commentV2BL.getParentCommentsByCourseId(courseId);
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
            List<CommentUserDTO> comments = commentV2BL.getChildComments(parentCommentId);
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
            int count = commentV2BL.countChildComments(parentCommentId);
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
            List<CommentUserDTO> comments = commentV2BL.getCommentsByCourseId(courseId);
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

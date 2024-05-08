package bo.com.knowix.api;

import bo.com.knowix.bl.CommentBL;
import bo.com.knowix.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentAPI {

    private final CommentBL commentBL;

    @Autowired
    public CommentAPI(CommentBL commentBL) {
        this.commentBL = commentBL;
    }

    @PostMapping("/parent")
    public ResponseEntity<?> createParentComment(
        @RequestParam("courseId") int courseId,
        @RequestBody CommentDTO commentDTO
    ) {
        try {
            CommentDTO createdComment = commentBL.createParentComment(courseId, commentDTO);
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/child")
    public ResponseEntity<?> createChildComment(
        @RequestParam("parentCommentId") int parentCommentId,
        @RequestBody CommentDTO commentDTO
    ) {
        try {
            CommentDTO createdComment = commentBL.createChildComment(parentCommentId, commentDTO);
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/course/{courseId}/parents")
    public ResponseEntity<?> getParentCommentsByCourseId(@PathVariable int courseId) {
        try {
            List<CommentDTO> comments = commentBL.getParentCommentsByCourseId(courseId);
            if (comments.isEmpty()) {
                return ResponseEntity.ok("No hay comentarios padres para este curso");
            }
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/parent/{parentCommentId}/children")
    public ResponseEntity<?> getChildComments(@PathVariable int parentCommentId) {
        try {
            List<CommentDTO> comments = commentBL.getChildComments(parentCommentId);
            if (comments.isEmpty()) {
                return ResponseEntity.ok("No hay comentarios hijos para este comentario padre");
            }
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/parent/{parentCommentId}/children/count")
    public ResponseEntity<?> countChildComments(@PathVariable int parentCommentId) {
        try {
            int count = commentBL.countChildComments(parentCommentId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getCommentsByCourseId(@PathVariable int courseId) {
        try {
            List<CommentDTO> comments = commentBL.getCommentsByCourseId(courseId);
            if (comments.isEmpty()) {
                return ResponseEntity.ok("No hay comentarios para este curso");
            }
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
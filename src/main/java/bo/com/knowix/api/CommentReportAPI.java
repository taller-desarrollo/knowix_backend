package bo.com.knowix.api;

import bo.com.knowix.bl.CommentReportBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/comment-report")
public class CommentReportAPI {

    private final CommentReportBL commentReportBL;
    private static final Logger LOGGER = Logger.getLogger(CommentReportAPI.class.getName());

    @Autowired
    public CommentReportAPI(CommentReportBL commentReportBL) {
        this.commentReportBL = commentReportBL;
    }

    // TODO: Implement pagination and filtering
    @GetMapping()
    public ResponseEntity<?> getCommentReports() {
        LOGGER.info("Starting process to get comment reports");
        try {
            LOGGER.info("Comment reports found successfully");
            return ResponseEntity.ok(commentReportBL.getCommentReports());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while getting comment reports: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            LOGGER.info("Finished process to get comment reports");
        }
    }

    @GetMapping("/{commentReportId}")
    public ResponseEntity<?> getCommentReportById(@PathVariable int commentReportId) {
        LOGGER.info("Starting process to get comment report by id");
        try {
            LOGGER.info("Comment report found successfully");
            return ResponseEntity.ok(commentReportBL.getCommentReportById(commentReportId));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while getting comment report by id: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            LOGGER.info("Finished process to get comment report by id");
        }
    }

    @PostMapping("/comment/{commentId}")
    public ResponseEntity<?> createCommentReport(
            @PathVariable int commentId,
            @RequestBody Map<String, String> body) {
        LOGGER.info("Starting process to create comment report");
        try {
            String commentReportReason = body.get("commentReportReason");
            LOGGER.info("Comment report created successfully");
            return ResponseEntity.ok(commentReportBL.createCommentReport(commentId, commentReportReason));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while creating comment report: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            LOGGER.info("Finished process to create comment report");
        }
    }

    @PutMapping("/{commentReportId}")
    public ResponseEntity<?> updateCommentReport(
            @PathVariable int commentReportId,
            @RequestBody Map<String, String> body) {
        LOGGER.info("Starting process to update comment report");
        try {
            // extract status from the map
            String status = body.get("status");
            LOGGER.info("Comment report updated successfully");
            return ResponseEntity.ok(commentReportBL.updateCommentReport(commentReportId, status));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while updating comment report: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            LOGGER.info("Finished process to update comment report");
        }
    }
}

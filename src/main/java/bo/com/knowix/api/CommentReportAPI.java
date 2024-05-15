package bo.com.knowix.api;

import bo.com.knowix.bl.CommentReportBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/comment-report")
public class CommentReportAPI {


    private final CommentReportBL commentReportBL;

    @Autowired
    public CommentReportAPI(CommentReportBL commentReportBL) {
        this.commentReportBL = commentReportBL;
    }

    //TODO: Implement pagination and filtering
    @GetMapping()
    public ResponseEntity<?> getCommentReports() {
        try {
            return ResponseEntity.ok(commentReportBL.getCommentReports());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{commentReportId}")
    public ResponseEntity<?> getCommentReportById(@PathVariable int commentReportId) {
        try {
            return ResponseEntity.ok(commentReportBL.getCommentReportById(commentReportId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/comment/{commentId}")
    public ResponseEntity<?> createCommentReport(
            @PathVariable int commentId,
            @RequestBody Map<String, String> body
    ) {
        try {
            String commentReportReason = body.get("commentReportReason");
            return ResponseEntity.ok(commentReportBL.createCommentReport(commentId, commentReportReason));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{commentReportId}")
    public ResponseEntity<?> updateCommentReport(
            @PathVariable int commentReportId,
            @RequestBody Map<String, String> body
    ) {
        try {
            //extract status from the map
            String status = body.get("status");
            return ResponseEntity.ok(commentReportBL.updateCommentReport(commentReportId, status));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

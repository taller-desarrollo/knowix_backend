package bo.com.knowix.api;

import bo.com.knowix.bl.ContentReportBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/content-report")
public class ContentReportAPI {

    private final ContentReportBL contentReportBL;

    @Autowired
    public ContentReportAPI(ContentReportBL contentReportBL) {
        this.contentReportBL = contentReportBL;
    }

    @GetMapping()
    public ResponseEntity<?> getContentReports() {
        try {
            return ResponseEntity.ok(contentReportBL.getContentReports());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{contentReportId}")
    public ResponseEntity<?> getContentReportById(@PathVariable int contentReportId) {
        try {
            return ResponseEntity.ok(contentReportBL.getContentReportById(contentReportId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/content/{contentId}")
    public ResponseEntity<?> createContentReport(@PathVariable int contentId, @RequestBody String contentReportReason) {
        try {
            return ResponseEntity.ok(contentReportBL.createContentReport(contentId, contentReportReason));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{contentReportId}")
    public ResponseEntity<?> updateContentReport(@PathVariable int contentReportId, @RequestBody String status) {
        try {
            return ResponseEntity.ok(contentReportBL.updateContentReport(contentReportId, status));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

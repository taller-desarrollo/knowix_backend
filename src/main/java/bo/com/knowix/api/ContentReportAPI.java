package bo.com.knowix.api;

import bo.com.knowix.bl.ContentReportBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/content-report")
public class ContentReportAPI {

    private final ContentReportBL contentReportBL;
    private static final Logger LOGGER = Logger.getLogger(ContentReportAPI.class.getName());

    @Autowired
    public ContentReportAPI(ContentReportBL contentReportBL) {
        this.contentReportBL = contentReportBL;
    }

    @GetMapping()
    public ResponseEntity<?> getContentReports() {
        LOGGER.info("Starting process to get content reports");
        try {
            LOGGER.info("Content reports found successfully");
            return ResponseEntity.ok(contentReportBL.getContentReports());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while getting content reports: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            LOGGER.info("Finished process to get content reports");
        }
    }

    @GetMapping("/{contentReportId}")
    public ResponseEntity<?> getContentReportById(@PathVariable int contentReportId) {
        LOGGER.info("Starting process to get content report by id");
        try {
            LOGGER.info("Content report found successfully");
            return ResponseEntity.ok(contentReportBL.getContentReportById(contentReportId));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while getting content report by id: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            LOGGER.info("Finished process to get content report by id");
        }
    }

    @PostMapping("/content/{contentId}")
    public ResponseEntity<?> createContentReport(
            @PathVariable int contentId,
            @RequestBody Map<String, String> body) {
        LOGGER.info("Starting process to create content report");
        try {
            String contentReportReason = body.get("contentReportReason");
            LOGGER.info("Content report created successfully");
            return ResponseEntity.ok(contentReportBL.createContentReport(contentId, contentReportReason));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while creating content report: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            LOGGER.info("Finished process to create content report");
        }
    }

    @PutMapping("/{contentReportId}")
    public ResponseEntity<?> updateContentReport(
            @PathVariable int contentReportId,
            @RequestBody Map<String, String> body) {
        LOGGER.info("Starting process to update content report");
        try {
            // extract status from the map
            String status = body.get("status");
            LOGGER.info("Content report updated successfully");
            return ResponseEntity.ok(contentReportBL.updateContentReport(contentReportId, status));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while updating content report: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            LOGGER.info("Finished process to update content report");
        }
    }
}

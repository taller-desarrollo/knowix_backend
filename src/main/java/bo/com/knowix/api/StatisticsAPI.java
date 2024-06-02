package bo.com.knowix.api;

import bo.com.knowix.bl.StatisticsBL;
import bo.com.knowix.dto.BarCharData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticsAPI {

    private final StatisticsBL statisticsBl;

    @Autowired
    public StatisticsAPI(StatisticsBL statisticsBl) {
        this.statisticsBl = statisticsBl;
    }

    Logger logger = LoggerFactory.getLogger(StatisticsAPI.class);

    // Top selling courses by dates
    @GetMapping("/top-selling-courses")
    public ResponseEntity<?> getTopSellingCourses(
            //Query params start date and end date
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestHeader("X-UUID") String kcUserKcUuid
    ){
        logger.info("Starting process to get top selling courses");
        try {
            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.atStartOfDay().plusDays(1).minusSeconds(1);
            Timestamp startTimestamp = Timestamp.valueOf(startDateTime);
            Timestamp endTimestamp = Timestamp.valueOf(endDateTime);
            List<BarCharData> data = statisticsBl.getTopSellingCourses(startTimestamp, endTimestamp, kcUserKcUuid);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            logger.error("Error occurred while getting top selling courses: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            logger.info("Finished process to get top selling courses");
        }
    }

}

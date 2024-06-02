package bo.com.knowix.bl;

import bo.com.knowix.dao.CourseDAO;
import bo.com.knowix.dao.PurchaseDAO;
import bo.com.knowix.dao.ReplyDAO;
import bo.com.knowix.dao.repository.KcUserRepository;
import bo.com.knowix.dto.BarCharData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsBL {

    private final ReplyDAO replyDAO;

    private final PurchaseDAO purchaseDAO;
    private final CourseDAO courseDAO;
    private final KcUserRepository kcUserRepository;

    private Logger logger = LoggerFactory.getLogger(StatisticsBL.class);

    @Autowired
    public StatisticsBL(ReplyDAO replyDAO, PurchaseDAO purchaseDAO, CourseDAO courseDAO, KcUserRepository kcUserRepository) {
        this.replyDAO = replyDAO;
        this.purchaseDAO = purchaseDAO;
        this.courseDAO = courseDAO;
        this.kcUserRepository = kcUserRepository;
    }

    public List<BarCharData> getTopSellingCourses(Timestamp startDate, Timestamp endDate, String kcUserKcUuid) {
        logger.info("Getting top selling courses with dates: " + startDate + " - " + endDate + " for user: " + kcUserKcUuid);
        List<Object[]> topSellingCourses = replyDAO.findByStatusIsTrueAndDateBetween(startDate, endDate, kcUserKcUuid);
        List<BarCharData> barCharDataList = new ArrayList<>();
        for (Object[] topSellingCourse : topSellingCourses) {
            BarCharData barCharData = new BarCharData();
            barCharData.setLabel((String) topSellingCourse[0]);
            barCharData.setY((Long) topSellingCourse[1]);
            barCharData.setEarnings((BigDecimal) topSellingCourse[2]);
            barCharDataList.add(barCharData);
        }
        return barCharDataList;
    }

}

package bo.com.knowix.bl;

import bo.com.knowix.dao.CourseDAO;
import bo.com.knowix.dao.PurchaseDAO;
import bo.com.knowix.dao.ReplyDAO;
import bo.com.knowix.dao.repository.KcUserRepository;
import bo.com.knowix.dto.BarCharData;
import org.jetbrains.annotations.NotNull;
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
        return getBarCharData(topSellingCourses);
    }

    public List<BarCharData> getSellsByMonths(Integer year, String kcUserKcUuid) {
        logger.info("Getting sells by months for year: " + year + " for user: " + kcUserKcUuid);
        List<Object[]> sellsByMonths = replyDAO.findSellsByMonths(year, kcUserKcUuid);
        return getBarCharData(sellsByMonths);
    }

    @NotNull
    private List<BarCharData> getBarCharData(List<Object[]> sellsByMonths) {
        List<BarCharData> barCharDataList = new ArrayList<>();
        for (Object[] sellsByMonth : sellsByMonths) {
            BarCharData barCharData = new BarCharData();
            barCharData.setLabel((String) sellsByMonth[0]);
            barCharData.setY((Long) sellsByMonth[1]);
            barCharData.setEarnings((BigDecimal) sellsByMonth[2]);
            barCharDataList.add(barCharData);
        }
        return barCharDataList;
    }

}

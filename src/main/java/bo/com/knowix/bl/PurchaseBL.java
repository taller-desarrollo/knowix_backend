package bo.com.knowix.bl;

import bo.com.knowix.dao.PurchaseDAO;
import bo.com.knowix.dto.PurchaseDTO;
import bo.com.knowix.dto.CourseDTO;
import bo.com.knowix.dto.PaymentMethodDTO;
import bo.com.knowix.entity.PurchaseEntity;
import bo.com.knowix.entity.CourseEntity;
import bo.com.knowix.entity.PaymentMethodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import bo.com.knowix.dao.PurchaseDAO;
import bo.com.knowix.dao.CourseDAO;
import bo.com.knowix.dao.PaymentMethodDAO;
import bo.com.knowix.dto.PurchaseDTO;
import bo.com.knowix.entity.PurchaseEntity;
import bo.com.knowix.entity.CourseEntity;
import bo.com.knowix.entity.PaymentMethodEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;

@Service
public class PurchaseBL {


    private final PurchaseDAO purchaseDAO;
    private final CourseDAO courseDAO;
    private final PaymentMethodDAO paymentMethodDAO;

    @Autowired
    public PurchaseBL(PurchaseDAO purchaseDAO, CourseDAO courseDAO, PaymentMethodDAO paymentMethodDAO) {
        this.purchaseDAO = purchaseDAO;
        this.courseDAO = courseDAO;
        this.paymentMethodDAO = paymentMethodDAO;
    }

    @Transactional
    public PurchaseDTO createPurchase(int courseId, int paymentMethodId, PurchaseDTO dto) {
        CourseEntity course = courseDAO.findById(courseId).orElseThrow();
        PaymentMethodEntity paymentMethod = paymentMethodDAO.findById(paymentMethodId).orElseThrow();

        PurchaseEntity entity = new PurchaseEntity();
        entity.setDatePurchase(dto.getDatePurchase());
        entity.setAmount(dto.getAmount());
        entity.setImageComprobante(dto.getImageComprobante());
        entity.setCourse(course);
        entity.setPaymentMethod(paymentMethod);
        entity.setKcUserKcUuid(dto.getKcUserKcUuid());


        return dto;
    }


    public List<PurchaseDTO> findAllPurchases() {
        return purchaseDAO.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PurchaseDTO findPurchaseById(int purchaseId) {
        Optional<PurchaseEntity> purchaseEntityOptional = purchaseDAO.findById(purchaseId);
        if (purchaseEntityOptional.isPresent()) {
            PurchaseEntity purchaseEntity = purchaseEntityOptional.get();
            return convertToDTO(purchaseEntity);
        } else {
            return null;
        }
    }

    public List<PurchaseDTO> findCoursesSoldBySeller(String sellerId) {
        return purchaseDAO.findAll().stream()
                .filter(purchase -> purchase.getCourse().getKcUserKcUuid().equals(sellerId))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PurchaseDTO> findPurchasesByBuyerId(String buyerId) {
        return purchaseDAO.findAll().stream()
                .filter(purchase -> purchase.getKcUserKcUuid().equals(buyerId))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    private PurchaseDTO convertToDTO(PurchaseEntity entity) {
        if (entity == null) {
            return null;
        }
        return new PurchaseDTO(
            entity.getPurchaseId(),
            entity.getDatePurchase(),
            entity.getAmount(),
            entity.getImageComprobante(), // Aquí se utiliza getImageComprobant()
            convertCourseToDTO(entity.getCourse()),
            convertPaymentMethodToDTO(entity.getPaymentMethod()),
            entity.getKcUserKcUuid()
        );
    }

    private CourseDTO convertCourseToDTO(CourseEntity course) {
        if (course == null) {
            return null;
        }
        int categoryID = (course.getCategory() != null) ? course.getCategory().getCategoryId() : 0;
        int languageID = (course.getLanguage() != null) ? course.getLanguage().getLanguageId() : 0;
        return new CourseDTO(
            course.getCourseId(),
            course.getCourseDescription(),
            course.getCourseStandardPrice(),
            course.getCourseName(),
            course.getCourseRequirements(),
            course.getStatus(),
            categoryID,
            languageID,
            course.getKcUserKcUuid()
        );
    }

    private PaymentMethodDTO convertPaymentMethodToDTO(PaymentMethodEntity paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }
        return new PaymentMethodDTO(
            paymentMethod.getPaymentMethodId(),
            paymentMethod.getCiPerson(),
            paymentMethod.getNameOwner(),
            paymentMethod.getPhoneNumber(),
            paymentMethod.getQrImage(),
            paymentMethod.getAccountNumber(),
            paymentMethod.getBankBankId(),
            paymentMethod.getKcUserKcUuid(),
            paymentMethod.getAccountTypeAccountTypeId()
        );
    }

    private PurchaseEntity convertToEntity(PurchaseDTO dto) {
        if (dto == null) {
            return null;
        }
        PurchaseEntity entity = new PurchaseEntity();
        entity.setDatePurchase(dto.getDatePurchase());
        entity.setAmount(dto.getAmount());
        entity.setImageComprobante(dto.getImageComprobante()); // Aquí se utiliza getImageComprobant()
        return entity;
    }
}

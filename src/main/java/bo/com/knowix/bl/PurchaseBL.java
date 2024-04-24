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


@Service
public class PurchaseBL {

    private final PurchaseDAO purchaseDAO;

    @Autowired
    public PurchaseBL(PurchaseDAO purchaseDAO) {
        this.purchaseDAO = purchaseDAO;
    }

    public List<PurchaseDTO> findAllPurchases() {
        return purchaseDAO.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    //para conseguir purchase por id:
    public PurchaseDTO findPurchaseById(int purchaseId) {
        Optional<PurchaseEntity> purchaseEntityOptional = purchaseDAO.findById(purchaseId);
        if (purchaseEntityOptional.isPresent()) {
            PurchaseEntity purchaseEntity = purchaseEntityOptional.get();
            return convertToDTO(purchaseEntity);
        } else {
            return null; // or throw an exception or handle the case accordingly
        }
    }



    private PurchaseDTO convertToDTO(PurchaseEntity entity) {
        if (entity == null) {
            return null;
        }
        return new PurchaseDTO(
            entity.getPurchaseId(),
            entity.getDatePurchase(),
            entity.getAmount(),
            entity.getImageComprobante(),
            convertCourseToDTO(entity.getCourse()),
            convertPaymentMethodToDTO(entity.getPaymentMethod()),
            entity.getKcUserKcUuid()
        );
    }

    private CourseDTO convertCourseToDTO(CourseEntity course) {
        if (course == null) {
            return null;
        }
        // Assuming Category and Language are also entities that have IDs
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
}

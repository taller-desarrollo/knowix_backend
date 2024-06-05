package bo.com.knowix.api;

import bo.com.knowix.bl.PurchaseBL;
import bo.com.knowix.dto.PurchaseDTO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseAPI {

    private final PurchaseBL purchaseBL;
    private static final Logger LOGGER = Logger.getLogger(PurchaseAPI.class.getName());

    @Autowired
    public PurchaseAPI(PurchaseBL purchaseBL) {
        this.purchaseBL = purchaseBL;
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<?> createPurchase(
            @RequestParam("amount") double amount,
            @RequestParam("kcUserKcUuid") String kcUserKcUuid,
            @RequestParam("courseId") int courseId,
            @RequestParam("paymentMethodId") int paymentMethodId,
            @RequestParam("datePurchase") String datePurchaseStr,
            @RequestParam("Image") MultipartFile image) {
        LOGGER.info("Starting process to create purchase");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            Timestamp datePurchase = Timestamp.valueOf(LocalDateTime.parse(datePurchaseStr, formatter));

            String filePath = saveComprobantImage(image);
            PurchaseDTO purchaseDTO = new PurchaseDTO();
            purchaseDTO.setAmount(BigDecimal.valueOf(amount));
            purchaseDTO.setKcUserKcUuid(kcUserKcUuid);
            purchaseDTO.setDatePurchase(datePurchase);
            purchaseDTO.setImageComprobante(filePath);

            PurchaseDTO created = purchaseBL.createPurchase(courseId, paymentMethodId, purchaseDTO);
            LOGGER.info("Purchase created successfully with ID: " + created.getPurchaseId());
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while creating purchase: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } finally {
            LOGGER.info("Finished process to create purchase");
        }
    }

    private String saveComprobantImage(MultipartFile image) throws IOException {
        String uploadDir = "bdd/comprobant/";
        String fileName = "comprobant_" + System.currentTimeMillis() + ".png";
        String filePath = uploadDir + fileName;
        File fileToSave = new File(filePath);
        FileUtils.writeByteArrayToFile(fileToSave, image.getBytes());
        return filePath;
    }

    @GetMapping
    public ResponseEntity<?> getAllPurchases() {
        LOGGER.info("Starting process to fetch all purchases");
        try {
            List<PurchaseDTO> purchases = purchaseBL.findAllPurchases();
            if (purchases.isEmpty()) {
                String message = "No hay registros para esta solicitud";
                LOGGER.info("No purchases found");
                return ResponseEntity.ok().body(message);
            }
            LOGGER.info("Purchases fetched successfully");
            return new ResponseEntity<>(purchases, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while fetching all purchases: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            LOGGER.info("Finished process to fetch all purchases");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> getPurchaseById(@PathVariable int id) {
        LOGGER.info("Starting process to fetch purchase by ID: " + id);
        try {
            PurchaseDTO purchase = purchaseBL.findPurchaseById(id);
            if (purchase != null) {
                LOGGER.info("Purchase fetched successfully");
                return new ResponseEntity<>(purchase, HttpStatus.OK);
            } else {
                LOGGER.info("No purchase found for ID: " + id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while fetching purchase by ID: " + id + " - " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Finished process to fetch purchase by ID: " + id);
        }
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<?> getCoursesSoldBySeller(@PathVariable String sellerId) {
        LOGGER.info("Starting process to fetch courses sold by seller ID: " + sellerId);
        try {
            List<PurchaseDTO> purchases = purchaseBL.findCoursesSoldBySeller(sellerId);
            if (!purchases.isEmpty()) {
                LOGGER.info("Courses sold fetched successfully");
                return new ResponseEntity<>(purchases, HttpStatus.OK);
            } else {
                String message = "No hay registros para esta solicitud";
                LOGGER.info("No courses sold found for seller ID: " + sellerId);
                return ResponseEntity.ok().body(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning(
                    "Error occurred while fetching courses sold by seller ID: " + sellerId + " - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            LOGGER.info("Finished process to fetch courses sold by seller ID: " + sellerId);
        }
    }

    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<?> getPurchasesByBuyerId(@PathVariable String buyerId) {
        LOGGER.info("Starting process to fetch purchases by buyer ID: " + buyerId);
        try {
            List<PurchaseDTO> purchases = purchaseBL.findPurchasesByBuyerId(buyerId);
            if (!purchases.isEmpty()) {
                LOGGER.info("Purchases fetched successfully");
                return new ResponseEntity<>(purchases, HttpStatus.OK);
            } else {
                String message = "No hay registros para esta solicitud";
                LOGGER.info("No purchases found for buyer ID: " + buyerId);
                return ResponseEntity.ok().body(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while fetching purchases by buyer ID: " + buyerId + " - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            LOGGER.info("Finished process to fetch purchases by buyer ID: " + buyerId);
        }
    }
}

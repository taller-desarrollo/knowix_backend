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



@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseAPI {

    private final PurchaseBL purchaseBL;

    @Autowired
    public PurchaseAPI(PurchaseBL purchaseBL) {
        this.purchaseBL = purchaseBL;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createPurchase(
        @RequestParam("amount") double amount,
        @RequestParam("kcUserKcUuid") String kcUserKcUuid,
        @RequestParam("courseId") int courseId,
        @RequestParam("paymentMethodId") int paymentMethodId,
        @RequestParam("datePurchase") String datePurchaseStr,
        @RequestParam("Image") MultipartFile image) {
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
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
        try {
            List<PurchaseDTO> purchases = purchaseBL.findAllPurchases();
            if (purchases.isEmpty()) {
                String message = "No hay registros para esta solicitud";
                return ResponseEntity.ok().body(message);
            }
            return new ResponseEntity<>(purchases, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> getPurchaseById(@PathVariable int id) {
        PurchaseDTO purchase = purchaseBL.findPurchaseById(id);
        if (purchase != null) {
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<?> getCoursesSoldBySeller(@PathVariable String sellerId) {
        try {
            List<PurchaseDTO> purchases = purchaseBL.findCoursesSoldBySeller(sellerId);
            if (!purchases.isEmpty()) {
                return new ResponseEntity<>(purchases, HttpStatus.OK);
            } else {
                String message = "No hay registros para esta solicitud";
                return ResponseEntity.ok().body(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<?> getPurchasesByBuyerId(@PathVariable String buyerId) {
        try {
            List<PurchaseDTO> purchases = purchaseBL.findPurchasesByBuyerId(buyerId);
            if (!purchases.isEmpty()) {
                return new ResponseEntity<>(purchases, HttpStatus.OK);
            } else {
                String message = "No hay registros para esta solicitud";
                return ResponseEntity.ok().body(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

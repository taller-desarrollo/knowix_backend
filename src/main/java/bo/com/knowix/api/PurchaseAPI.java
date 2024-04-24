package bo.com.knowix.api;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import bo.com.knowix.bl.LanguageBL;
import bo.com.knowix.entity.LanguageEntity;
import bo.com.knowix.bl.PurchaseBL;
import bo.com.knowix.dto.PurchaseDTO;
import bo.com.knowix.entity.PurchaseEntity;

import org.springframework.http.HttpStatus;
import bo.com.knowix.dto.CourseDTO;

@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseAPI {
    
    private final LanguageBL languageBL;
    private final PurchaseBL purchaseBL;
    private static final Logger LOGGER = Logger.getLogger(PurchaseAPI.class.getName());

    @Autowired
    public PurchaseAPI(LanguageBL languageBL, PurchaseBL purchaseBL) {
        this.languageBL = languageBL;
        this.purchaseBL = purchaseBL;
    }

    @GetMapping
    public ResponseEntity<?> getAllPurchases(@RequestHeader(value="Authorization", required = false) String token) {
        LOGGER.info("Token recibido: " + token);
        LOGGER.info("Iniciando el proceso de obtener todos los purchase");
        try {
            List<PurchaseDTO> purchases = purchaseBL.findAllPurchases();
            if (purchases.isEmpty()) {
                LOGGER.info("cantidad de purchase: " + purchases.size());
                String message = "No hay registros para esta solicitud";
                return ResponseEntity.ok().body(message);
            }
            return new ResponseEntity<>(purchases, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.severe("Error al obtener los purchase: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
        List<PurchaseDTO> purchases = purchaseBL.findCoursesSoldBySeller(sellerId);
        if (!purchases.isEmpty()) {
            return new ResponseEntity<>(purchases, HttpStatus.OK);
        } else {
            String message = "No hay registros para esta solicitud";
            return ResponseEntity.ok().body(message);
        }
    }

    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<?> getPurchasesByBuyerId(@PathVariable String buyerId) {
        List<PurchaseDTO> purchases = purchaseBL.findPurchasesByBuyerId(buyerId);
        if (!purchases.isEmpty()) {
            return new ResponseEntity<>(purchases, HttpStatus.OK);
        } else {
            String message = "No hay registros para esta solicitud";
            return ResponseEntity.ok().body(message);
        }
    }
}

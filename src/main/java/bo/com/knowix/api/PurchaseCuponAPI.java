package bo.com.knowix.api;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import bo.com.knowix.bl.PurchaseCuponBL;
import bo.com.knowix.dto.PurchaseCuponDTO;

@RestController
@RequestMapping("/api/purchase-cupons")
public class PurchaseCuponAPI {

    private final PurchaseCuponBL purchaseCuponBL;
    private static final Logger LOGGER = Logger.getLogger(PurchaseCuponAPI.class.getName());

    @Autowired
    public PurchaseCuponAPI(PurchaseCuponBL purchaseCuponBL) {
        this.purchaseCuponBL = purchaseCuponBL;
    }

    @PostMapping
    public ResponseEntity<PurchaseCuponDTO> createPurchaseCupon(@RequestBody PurchaseCuponDTO purchaseCuponDTO) {
        LOGGER.info("Init request to create purchaseCupon");
        try {
            PurchaseCuponDTO createdPurchaseCupon = purchaseCuponBL.createPurchaseCupon(purchaseCuponDTO);
            LOGGER.info("PurchaseCupon created successfully");
            return ResponseEntity.ok(createdPurchaseCupon);
        } catch (Exception e) {
            LOGGER.severe("Error to create purchaseCupon");
            LOGGER.severe(e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Request to create purchaseCupon");
        }
    }

    @GetMapping("/purchase/{purchasePurchaseId}")
    public ResponseEntity<List<PurchaseCuponDTO>> getPurchaseCuponsByPurchaseId(@PathVariable Integer purchasePurchaseId) {
        LOGGER.info("Init request to get purchaseCupons by purchaseId");
        try {
            List<PurchaseCuponDTO> purchaseCupons = purchaseCuponBL.findPurchaseCuponsByPurchasePurchaseId(purchasePurchaseId);
            LOGGER.info("PurchaseCupons found successfully");
            return ResponseEntity.ok(purchaseCupons);
        } catch (Exception e) {
            LOGGER.severe("Error to get purchaseCupons by purchaseId");
            LOGGER.severe(e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Request to get purchaseCupons by purchaseId");
        }
    }

    @GetMapping
    public ResponseEntity<List<PurchaseCuponDTO>> getAllPurchaseCupons() {
        LOGGER.info("Init request to get all purchaseCupons");
        try {
            List<PurchaseCuponDTO> purchaseCupons = purchaseCuponBL.findAllPurchaseCupons();
            LOGGER.info("PurchaseCupons found successfully");
            return ResponseEntity.ok(purchaseCupons);
        } catch (Exception e) {
            LOGGER.severe("Error to get all purchaseCupons");
            LOGGER.severe(e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Request to get all purchaseCupons");
        }
    }

    @GetMapping("/cupon/{cuponCuponId}")
    public ResponseEntity<List<PurchaseCuponDTO>> getPurchaseCuponsByCuponId(@PathVariable Integer cuponCuponId) {
        LOGGER.info("Init request to get purchaseCupons by cuponId");
        try {
            List<PurchaseCuponDTO> purchaseCupons = purchaseCuponBL.findPurchaseCuponsByCuponCuponId(cuponCuponId);
            LOGGER.info("PurchaseCupons found successfully");
            return ResponseEntity.ok(purchaseCupons);
        } catch (Exception e) {
            LOGGER.severe("Error to get purchaseCupons by cuponId");
            LOGGER.severe(e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Request to get purchaseCupons by cuponId");
        }
    }
}
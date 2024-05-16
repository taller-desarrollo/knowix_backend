package bo.com.knowix.api;

import bo.com.knowix.bl.PaymentMethodBL;
import bo.com.knowix.dto.PaymentMethodDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/paymentmethod")
public class PaymentMethodAPI {

    private final PaymentMethodBL paymentMethodBL;
    private final ObjectMapper objectMapper;
    private static final Logger LOGGER = Logger.getLogger(PaymentMethodAPI.class.getName());

    @Autowired
    public PaymentMethodAPI(PaymentMethodBL paymentMethodBL, ObjectMapper objectMapper) {
        this.paymentMethodBL = paymentMethodBL;
        this.objectMapper = objectMapper;
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<PaymentMethodDTO> createPaymentMethod(@RequestParam("paymentMethod") String paymentMethodJSON,
            @RequestParam("qrImage") MultipartFile qrImage) {
        LOGGER.info("Starting process to create payment method");
        try {
            PaymentMethodDTO paymentMethodDTO = objectMapper.readValue(paymentMethodJSON, PaymentMethodDTO.class);
            String filePath = saveQrImageFile(qrImage);
            paymentMethodDTO.setQrImage(filePath);
            PaymentMethodDTO created = paymentMethodBL.createPaymentMethod(paymentMethodDTO);
            LOGGER.info("Payment method created successfully");
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while creating payment method: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            LOGGER.info("Finished process to create payment method");
        }
    }

    private String saveQrImageFile(MultipartFile qrImage) throws IOException {
        String uploadDir = "bdd/qr/";
        String fileName = "qr_" + System.currentTimeMillis() + ".png";
        String filePath = uploadDir + fileName;
        File fileToSave = new File(filePath);
        FileUtils.writeByteArrayToFile(fileToSave, qrImage.getBytes());
        return filePath;
    }

    @PutMapping(value = "/user/{kcUserKcUuid}/{id}", consumes = { "multipart/form-data" })
    public ResponseEntity<PaymentMethodDTO> updatePaymentMethod(@PathVariable String kcUserKcUuid,
            @PathVariable int id,
            @RequestParam("paymentMethod") String paymentMethodJSON,
            @RequestParam("qrImage") MultipartFile qrImage) {
        LOGGER.info("Starting process to update payment method");
        try {
            PaymentMethodDTO paymentMethodDTO = objectMapper.readValue(paymentMethodJSON, PaymentMethodDTO.class);
            if (!qrImage.isEmpty()) {
                String filePath = saveQrImageFile(qrImage);
                paymentMethodDTO.setQrImage(filePath);
                LOGGER.info("QR image updated successfully");
            }
            LOGGER.info("Payment method updated successfully");
            return paymentMethodBL.updatePaymentMethod(kcUserKcUuid, id, paymentMethodDTO)
                    .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while updating payment method: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            LOGGER.info("Finished process to update payment method");
        }
    }

    @GetMapping("/user/{kcUserKcUuid}")
    public ResponseEntity<List<PaymentMethodDTO>> getPaymentMethodsByUserKcUuid(@PathVariable String kcUserKcUuid) {
        LOGGER.info("Starting process to get payment methods by user KC UUID");
        try {
            List<PaymentMethodDTO> paymentMethods = paymentMethodBL.findPaymentMethodsByKcUserKcUuid(kcUserKcUuid);
            if (paymentMethods.isEmpty()) {
                LOGGER.warning("No payment methods found for user KC UUID: " + kcUserKcUuid);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            LOGGER.info("Payment methods fetched successfully");
            return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while fetching payment methods: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Finished process to get payment methods by user KC UUID");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> getPaymentMethodById(@PathVariable int id) {
        LOGGER.info("Starting process to get payment method by ID");
        try {
            return paymentMethodBL.findPaymentMethodById(id)
                    .map(dto -> {
                        LOGGER.info("Payment method fetched successfully");
                        return new ResponseEntity<>(dto, HttpStatus.OK);
                    })
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while fetching payment method by ID: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Finished process to get payment method by ID");
        }
    }
}

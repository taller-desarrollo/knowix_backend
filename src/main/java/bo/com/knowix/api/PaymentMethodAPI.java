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

@RestController
@RequestMapping("/api/v1/paymentmethod")
public class PaymentMethodAPI {

    private final PaymentMethodBL paymentMethodBL;
    private final ObjectMapper objectMapper; 

    @Autowired
    public PaymentMethodAPI(PaymentMethodBL paymentMethodBL, ObjectMapper objectMapper) {
        this.paymentMethodBL = paymentMethodBL;
        this.objectMapper = objectMapper; 
    }

    @PostMapping(consumes = {"multipart/form-data"}) 
    public ResponseEntity<PaymentMethodDTO> createPaymentMethod(@RequestParam("paymentMethod") String paymentMethodJSON,
                                                                @RequestParam("qrImage") MultipartFile qrImage) {
        try {
            PaymentMethodDTO paymentMethodDTO = objectMapper.readValue(paymentMethodJSON, PaymentMethodDTO.class);

            String filePath = saveQrImageFile(qrImage);
            paymentMethodDTO.setQrImage(filePath);

            PaymentMethodDTO created = paymentMethodBL.createPaymentMethod(paymentMethodDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); 
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    @PutMapping(value = "/user/{kcUserKcUuid}/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<PaymentMethodDTO> updatePaymentMethod(@PathVariable String kcUserKcUuid,
                                                                @PathVariable int id,
                                                                @RequestParam("paymentMethod") String paymentMethodJSON,
                                                                @RequestParam("qrImage") MultipartFile qrImage) {
        try {
            PaymentMethodDTO paymentMethodDTO = objectMapper.readValue(paymentMethodJSON, PaymentMethodDTO.class);

            if (!qrImage.isEmpty()) {
                String filePath = saveQrImageFile(qrImage);
                paymentMethodDTO.setQrImage(filePath);
            }

            return paymentMethodBL.updatePaymentMethod(kcUserKcUuid, id, paymentMethodDTO)
                    .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace(); 
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{kcUserKcUuid}")
    public ResponseEntity<List<PaymentMethodDTO>> getPaymentMethodsByUserKcUuid(@PathVariable String kcUserKcUuid) {
        try {
            List<PaymentMethodDTO> paymentMethods = paymentMethodBL.findPaymentMethodsByKcUserKcUuid(kcUserKcUuid);
            if (paymentMethods.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> getPaymentMethodById(@PathVariable int id) {
        return paymentMethodBL.findPaymentMethodById(id)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

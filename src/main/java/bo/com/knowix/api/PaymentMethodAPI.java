package bo.com.knowix.api;

import bo.com.knowix.bl.PaymentMethodBL;
import bo.com.knowix.dto.PaymentMethodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paymentmethod")
public class PaymentMethodAPI {

    private final PaymentMethodBL paymentMethodBL;

    @Autowired
    public PaymentMethodAPI(PaymentMethodBL paymentMethodBL) {
        this.paymentMethodBL = paymentMethodBL;
    }

    @PostMapping()
    public ResponseEntity<PaymentMethodDTO> createPaymentMethod(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        try {
            PaymentMethodDTO created = paymentMethodBL.createPaymentMethod(paymentMethodDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
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

    @PutMapping("/user/{kcUserKcUuid}/{id}")
    public ResponseEntity<PaymentMethodDTO> updatePaymentMethod(@PathVariable String kcUserKcUuid, @PathVariable int id, @RequestBody PaymentMethodDTO paymentMethodDTO) {
        try {
            return paymentMethodBL.updatePaymentMethod(kcUserKcUuid, id, paymentMethodDTO)
                    .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> getPaymentMethodById(@PathVariable int id) {
        return paymentMethodBL.findPaymentMethodById(id)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

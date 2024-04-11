package bo.com.knowix.api;

import bo.com.knowix.bl.BankBL;
import bo.com.knowix.entity.BankEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/bank")
public class BankAPI {

    private final BankBL bankBL;
    private static final Logger LOGGER = Logger.getLogger(BankAPI.class.getName());

    @Autowired
    public BankAPI(BankBL bankBL) {
        this.bankBL = bankBL;
    }

    @GetMapping()
    public ResponseEntity<List<BankEntity>> getAllBanks() {
        LOGGER.info("Starting process to fetch all banks");
        try {
            List<BankEntity> banks = bankBL.findAllBanks();
            return ResponseEntity.ok(banks);
        } catch (Exception e) {
            LOGGER.warning("Error occurred while fetching banks: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to fetch all banks");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankEntity> getBankById(@PathVariable("id") int bankId) {
        LOGGER.info("Starting process to fetch bank by ID: " + bankId);
        try {
            Optional<BankEntity> bank = bankBL.findBankById(bankId);
            if (bank.isPresent()) {
                return ResponseEntity.ok(bank.get());
            } else {
                LOGGER.info("Bank not found with ID: " + bankId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            LOGGER.warning("Error occurred while fetching bank by ID: " + bankId + " - " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to fetch bank by ID: " + bankId);
        }
    }
}

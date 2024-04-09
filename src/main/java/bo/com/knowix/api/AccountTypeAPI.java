package bo.com.knowix.api;

import bo.com.knowix.bl.AccountTypeBL;
import bo.com.knowix.entity.AccountTypeEntity;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounttype")
public class AccountTypeAPI {

    private final AccountTypeBL accountTypeBL;
    private static final Logger LOGGER = Logger.getLogger(AccountTypeAPI.class.getName());

    @Autowired
    public AccountTypeAPI(AccountTypeBL accountTypeBL) {
        this.accountTypeBL = accountTypeBL;
    }

    @GetMapping()
    public ResponseEntity<List<AccountTypeEntity>> getAllAccountTypes() {
        LOGGER.info("Starting process to fetch all account types");
        try {
            List<AccountTypeEntity> accountTypes = accountTypeBL.findAllAccountTypes();
            return ResponseEntity.ok(accountTypes);
        } catch (Exception e) {
            LOGGER.warning("Error occurred while fetching account types: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to fetch all account types");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTypeEntity> getAccountTypeById(@PathVariable("id") int accountTypeId) {
        LOGGER.info("Starting process to fetch account type by ID: " + accountTypeId);
        try {
            Optional<AccountTypeEntity> accountType = accountTypeBL.findAccountTypeById(accountTypeId);
            if (accountType.isPresent()) {
                return ResponseEntity.ok(accountType.get());
            } else {
                LOGGER.info("Account type not found with ID: " + accountTypeId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            LOGGER.warning("Error occurred while fetching account type by ID: " + accountTypeId + " - " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finished process to fetch account type by ID: " + accountTypeId);
        }
    }
}

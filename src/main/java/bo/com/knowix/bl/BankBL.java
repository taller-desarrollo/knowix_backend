package bo.com.knowix.bl;

import bo.com.knowix.dao.BankDAO;
import bo.com.knowix.entity.BankEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankBL {

    private final BankDAO bankDAO;

    @Autowired
    public BankBL(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

    public List<BankEntity> findAllBanks() {
        return bankDAO.findAll();
    }

    public Optional<BankEntity> findBankById(int bankId) {
        return bankDAO.findById(bankId);
    }
}

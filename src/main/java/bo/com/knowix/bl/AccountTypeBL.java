package bo.com.knowix.bl;

import bo.com.knowix.dao.AccountTypeDAO;
import bo.com.knowix.entity.AccountTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountTypeBL {

    private final AccountTypeDAO accountTypeDAO;

    @Autowired
    public AccountTypeBL(AccountTypeDAO accountTypeDAO) {
        this.accountTypeDAO = accountTypeDAO;
    }

    public List<AccountTypeEntity> findAllAccountTypes() {
        return accountTypeDAO.findAll();
    }

    public Optional<AccountTypeEntity> findAccountTypeById(int accountTypeId) {
        return accountTypeDAO.findById(accountTypeId);
    }
}

package bo.com.knowix.bl;

import bo.com.knowix.dao.LanguageDAO;
import bo.com.knowix.entity.LanguageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageBL {

    private final LanguageDAO languageDAO;

    @Autowired
    public LanguageBL(LanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }

    public List<LanguageEntity> findAllLanguages() {
        return languageDAO.findAll();
    }

    public Optional<LanguageEntity> findLanguageById(int languageId) {
        return languageDAO.findById(languageId);
    }
}

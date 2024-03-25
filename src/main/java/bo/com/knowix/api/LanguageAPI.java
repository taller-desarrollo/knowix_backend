package bo.com.knowix.api;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import bo.com.knowix.bl.LanguageBL;
import bo.com.knowix.entity.LanguageEntity;

@RestController
@RequestMapping("/api/v1/language")
public class LanguageAPI {
    
    private final LanguageBL languageBL;
    private static final Logger LOGGER = Logger.getLogger(LanguageAPI.class.getName());

    @Autowired
    public LanguageAPI(LanguageBL languageBL) {
        this.languageBL = languageBL;
    }

    @GetMapping()
    public ResponseEntity<List<LanguageEntity>> getAllLanguages(@RequestHeader(value="Authorization", required = false) String token) {
        LOGGER.info("Token recibido: " + token);
        LOGGER.info("Iniciando el proceso de obtener todos los idiomas");
        try {
            List<LanguageEntity> languages = languageBL.findAllLanguages();
            return ResponseEntity.ok(languages);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al obtener los idiomas: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finalizando el proceso de obtener todos los idiomas");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageEntity> getLanguageById(@PathVariable("id") int languageId, @RequestHeader(value="Authorization", required = false) String token) {
        LOGGER.info("Token recibido: " + token);
        LOGGER.info("Iniciando el proceso de obtener el idioma con ID: " + languageId);
        try {
            Optional<LanguageEntity> language = languageBL.findLanguageById(languageId);
            if (language.isPresent()) {
                return ResponseEntity.ok(language.get());
            } else {
                LOGGER.info("No se encontró el idioma con ID: " + languageId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al obtener el idioma con ID: " + languageId + " - " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finalizando el proceso de obtener el idioma con ID: " + languageId);
        }
    }

    @PostMapping()
    public ResponseEntity<LanguageEntity> addLanguage(@RequestBody LanguageEntity language, @RequestHeader(value="Authorization", required = false) String token) {
        LOGGER.info("Token recibido: " + token);
        LOGGER.info("Iniciando el proceso de agregar un nuevo idioma");
        try {
            LanguageEntity newLanguage = languageBL.addLanguage(language);
            return ResponseEntity.ok(newLanguage);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al agregar el nuevo idioma: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finalizando el proceso de agregar un nuevo idioma");
        }
    }
}

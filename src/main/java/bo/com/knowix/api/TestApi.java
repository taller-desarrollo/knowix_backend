package bo.com.knowix.api;

import bo.com.knowix.service.IKeycloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestApi {

    @Autowired
    private final IKeycloakService keycloakService;
    private Logger logger = LoggerFactory.getLogger(TestApi.class);

    public TestApi(IKeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @GetMapping("/test-educator")

    public String helloEducator(@RequestHeader("X-UUID") String kcuuid){
        logger.info("Keycloak UUID: " + kcuuid);
        return "Hello Soring Boot With Keycloak -Educator";
    }

    @GetMapping("/test-student")
    public String helloStudent(@RequestHeader("X-UUID") String kcuuid)
    {
        logger.info("Keycloak UUID: " + kcuuid);
        return "Hello Soring Boot With Keycloak -Student";
    }

    @GetMapping("/test-no-authenticated")
    public UserRepresentation helloEveryone(@RequestHeader("X-UUID") String kcuuid){
        logger.info("Keycloak UUID: " + kcuuid);
        return keycloakService.findUserBySubject(kcuuid);
    }
}

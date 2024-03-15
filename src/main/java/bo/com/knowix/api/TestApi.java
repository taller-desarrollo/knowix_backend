package bo.com.knowix.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/test")
public class TestApi {

    @GetMapping("/test-educator")
    public String helloEducator(){
        return "Hello Soring Boot With Keycloak -Educator";
    }

    @GetMapping("/test-student")
    public String helloStudent(){
        return "Hello Soring Boot With Keycloak -Student";
    }
}

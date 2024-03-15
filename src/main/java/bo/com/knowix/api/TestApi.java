package bo.com.knowix.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {

    @GetMapping("/test-educator")
    @PreAuthorize("hasRole('educator')")
    public String helloEducator(){
        return "Hello Soring Boot With Keycloak -Educator";
    }

    @GetMapping("/test-student")
    @PreAuthorize("hasRole('student') or hasRole('educator')")
    public String helloStudent(){
        return "Hello Soring Boot With Keycloak -Student";
    }
}

package bo.com.knowix.api;

import bo.com.knowix.bl.UserBl;
import bo.com.knowix.dto.UserDto;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserApi {

    private final UserBl userBl;
    private final Logger logger = LoggerFactory.getLogger(UserApi.class);

    public UserApi(UserBl userBl) {
        this.userBl = userBl;
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        UserRepresentation response = userBl.createUser(userDto);
        return ResponseEntity.ok(response);
    }

}

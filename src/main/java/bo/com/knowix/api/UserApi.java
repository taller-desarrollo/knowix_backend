package bo.com.knowix.api;

import bo.com.knowix.bl.UserBl;
import bo.com.knowix.dto.UserDto;
import bo.com.knowix.entity.KcUserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @RequestHeader("X-UUID") String kcuuid){
        UserRepresentation response = userBl.updateUser(userDto, kcuuid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/roles")
    public ResponseEntity<?> updateUserRoles(@RequestBody UserDto userDto , @RequestHeader("X-UUID") String kcuuid){
        UserRepresentation response = userBl.updateUserRoles(userDto, kcuuid);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<?> getUser(@RequestHeader("X-UUID") String kcuuid){
        KcUserEntity response = userBl.getUser(kcuuid);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userBl.getAllUsers());
    }

    @PutMapping("/block/{kcuuid}")
    public ResponseEntity<?> blockUser(@PathVariable String kcuuid){
        userBl.blockUser(kcuuid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/unblock/{kcuuid}")
    public ResponseEntity<?> unblockUser(@PathVariable String kcuuid){
        userBl.unblockUser(kcuuid);
        return ResponseEntity.ok().build();
    }


}

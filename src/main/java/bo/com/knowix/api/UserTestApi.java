package bo.com.knowix.api;

import bo.com.knowix.dto.UserDto;
import bo.com.knowix.service.IKeycloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/user-test")
public class UserTestApi {

    private final Logger logger = LoggerFactory.getLogger(UserTestApi.class);

    @Autowired
    private IKeycloakService keycloakService;

    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers() {
        logger.info("Finding all users");
        return ResponseEntity.ok(keycloakService.findAllUsers());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> findUsersByUsername(@PathVariable String username) {
        logger.info("Finding user by username: " + username);
        return ResponseEntity.ok(keycloakService.findUsersByUsername(username));
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws URISyntaxException {
        logger.info("Creating user: " + userDto);
        UserRepresentation response = keycloakService.createUser(userDto);
        return ResponseEntity.created(new URI("/api/v1/user-test/user")).body(response);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserDto userDto) {
        logger.info("Updating user: " + userDto);
        keycloakService.updateUser(userId, userDto);
        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        logger.info("Deleting user: " + userId);
        keycloakService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

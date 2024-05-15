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
        logger.info("Starting process to find all users");
        try {
            logger.info("Finding all users");
            return ResponseEntity.ok(keycloakService.findAllUsers());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while finding all users: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            logger.info("Finished process to find all users");
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> findUsersByUsername(@PathVariable String username) {
        logger.info("Starting process to find user by username");
        try {
            logger.info("Finding user by username: " + username);
            return ResponseEntity.ok(keycloakService.findUsersByUsername(username));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while finding user by username: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            logger.info("Finished process to find user by username");
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws URISyntaxException {
        logger.info("Starting process to create user");
        try {
            logger.info("Creating user: " + userDto);
            UserRepresentation response = keycloakService.createUser(userDto);
            logger.info("User created successfully");
            return ResponseEntity.created(new URI("/api/v1/user-test/user")).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while creating user: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            logger.info("Finished process to create user");
        }
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserDto userDto) {
        logger.info("Starting process to update user");
        try {
            logger.info("Updating user: " + userDto);
            keycloakService.updateUser(userId, userDto);
            logger.info("User updated successfully");
            return ResponseEntity.ok("User updated");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while updating user: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            logger.info("Finished process to update user");
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        logger.info("Starting process to delete user");
        try {
            logger.info("Deleting user: " + userId);
            keycloakService.deleteUser(userId);
            logger.info("User deleted successfully");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while deleting user: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            logger.info("Finished process to delete user");
        }
    }
}

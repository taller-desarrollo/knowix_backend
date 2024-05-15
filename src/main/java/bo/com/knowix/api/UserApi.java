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
        logger.info("Starting process to create user");
        try {
            UserRepresentation response = userBl.createUser(userDto);
            logger.info("User created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while creating user: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            logger.info("Finished process to create user");
        }
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @RequestHeader("X-UUID") String kcuuid) {
        logger.info("Starting process to update user");
        try {
            UserRepresentation response = userBl.updateUser(userDto, kcuuid);
            logger.info("User updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while updating user: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            logger.info("Finished process to update user");
        }
    }

    @PutMapping("/roles")
    public ResponseEntity<?> updateUserRoles(@RequestBody UserDto userDto, @RequestHeader("X-UUID") String kcuuid) {
        logger.info("Starting process to update user roles");
        try {
            UserRepresentation response = userBl.updateUserRoles(userDto, kcuuid);
            logger.info("User roles updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while updating user roles: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            logger.info("Finished process to update user roles");
        }
    }

    @GetMapping()
    public ResponseEntity<?> getUser(@RequestHeader("X-UUID") String kcuuid) {
        logger.info("Starting process to get user");
        try {
            KcUserEntity response = userBl.getUser(kcuuid);
            logger.info("User fetched successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while getting user: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            logger.info("Finished process to get user");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        logger.info("Starting process to get all users");
        try {
            logger.info("Users fetched successfully");
            return ResponseEntity.ok(userBl.getAllUsers());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while getting all users: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            logger.info("Finished process to get all users");
        }
    }

    @PutMapping("/block/{kcuuid}")
    public ResponseEntity<?> blockUser(@PathVariable String kcuuid) {
        logger.info("Starting process to block user");
        try {
            userBl.blockUser(kcuuid);
            logger.info("User blocked successfully");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while blocking user: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            logger.info("Finished process to block user");
        }
    }

    @PutMapping("/unblock/{kcuuid}")
    public ResponseEntity<?> unblockUser(@PathVariable String kcuuid) {
        logger.info("Starting process to unblock user");
        try {
            userBl.unblockUser(kcuuid);
            logger.info("User unblocked successfully");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while unblocking user: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } finally {
            logger.info("Finished process to unblock user");
        }
    }
}

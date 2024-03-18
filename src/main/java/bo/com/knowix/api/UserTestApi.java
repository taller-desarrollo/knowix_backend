package bo.com.knowix.api;

import bo.com.knowix.dto.UserDto;
import bo.com.knowix.service.IKeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/user-test")
public class UserTestApi {

    @Autowired
    private IKeycloakService keycloakService;

    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers() {
        return ResponseEntity.ok(keycloakService.findAllUsers());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> findUsersByUsername(@PathVariable String username) {
        return ResponseEntity.ok(keycloakService.findUsersByUsername(username));
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws URISyntaxException {
        String response = keycloakService.createUser(userDto);
        return ResponseEntity.created(new URI("/api/v1/user-test/user")).body(response);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserDto userDto) {
        keycloakService.updateUser(userId, userDto);
        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        keycloakService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

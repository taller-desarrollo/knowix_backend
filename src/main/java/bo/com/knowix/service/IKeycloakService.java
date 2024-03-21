package bo.com.knowix.service;

import bo.com.knowix.dto.UserDto;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface IKeycloakService {
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> findUsersByUsername(String username);
    UserRepresentation createUser(UserDto userDto);
    void deleteUser(String userId);
    UserRepresentation updateUser(String userId, UserDto userDto);
    UserRepresentation updateUserRoles(String userId, List<String> roles);
    UserRepresentation findUserBySubject(String subject);
}

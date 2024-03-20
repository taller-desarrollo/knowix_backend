package bo.com.knowix.service;

import bo.com.knowix.dto.UserDto;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface IKeycloakService {
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> findUsersByUsername(String username);
    UserRepresentation createUser(UserDto userDto);
    void deleteUser(String userId);
    void updateUser(String userId, UserDto userDto);
    UserRepresentation findUserBySubject(String subject);
}

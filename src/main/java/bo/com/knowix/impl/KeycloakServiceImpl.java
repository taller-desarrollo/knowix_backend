package bo.com.knowix.impl;

import bo.com.knowix.dto.UserDto;
import bo.com.knowix.service.IKeycloakService;
import bo.com.knowix.util.KeycloakProvider;
import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class KeycloakServiceImpl implements IKeycloakService {

    @Override
    public List<UserRepresentation> findAllUsers() {
        return KeycloakProvider.getRealmResource().users().list();
    }

    @Override
    public List<UserRepresentation> findUsersByUsername(String username) {
        return KeycloakProvider.getRealmResource().users().searchByUsername(username, true);
    }

    @Override
    public String createUser(@NonNull UserDto userDto) {
        int status = 0;
        UsersResource userResource = KeycloakProvider.getUserResource();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(userDto.getFirstName());
        userRepresentation.setLastName(userDto.getLastName());
        userRepresentation.setEmail(userDto.getEmail());
        userRepresentation.setUsername(userDto.getUsername());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);

        Response response = userResource.create(userRepresentation);
        status = response.getStatus();

        if (status == 201) {
            String path = response.getLocation().getPath();
            String userId = path.substring(path.lastIndexOf('/') + 1);
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(OAuth2Constants.PASSWORD);
            credentialRepresentation.setValue(userDto.getPassword());

            userResource.get(userId).resetPassword(credentialRepresentation);
            RealmResource realmResource = KeycloakProvider.getRealmResource();

            List<RoleRepresentation> roleRepresentations = null;

            if(userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
                roleRepresentations = List.of(realmResource.roles().get("user").toRepresentation());
                realmResource.users().get(userId).roles().realmLevel().add(roleRepresentations);
            } else {
                roleRepresentations = userDto.getRoles().stream().map(role -> realmResource.clients().get("knowix_frontend").roles().get(role).toRepresentation()).toList();
                realmResource.users().get(userId).roles().clientLevel("knowix_frontend").add(roleRepresentations);
            }

            return "User created successfully";
        }else if(status == 409) {
            return "User already exists";
        } else {
            return "Error creating user";
        }
    }

    @Override
    public void deleteUser(String userId) {
        KeycloakProvider.getUserResource().get(userId).remove();
    }

    @Override
    public void updateUser(String userId, UserDto userDto) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(OAuth2Constants.PASSWORD);
        credentialRepresentation.setValue(userDto.getPassword());

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(userDto.getFirstName());
        userRepresentation.setLastName(userDto.getLastName());
        userRepresentation.setEmail(userDto.getEmail());
        userRepresentation.setUsername(userDto.getUsername());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(List.of(credentialRepresentation));

        UserResource userResource = KeycloakProvider.getUserResource().get(userId);
        userResource.update(userRepresentation);

    }

    @Override
    public UserRepresentation findUserBySubject(String subject) {
        List<UserRepresentation> users = KeycloakProvider.getRealmResource().users().list();
        Optional<UserRepresentation> userOptional = users.stream()
                .filter(user -> user.getId().equals(subject))
                .findFirst();
        return userOptional.orElse(null);
    }
}

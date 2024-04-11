package bo.com.knowix.service.impl;

import bo.com.knowix.dto.UserDto;
import bo.com.knowix.service.IKeycloakService;
import bo.com.knowix.util.KeycloakProvider;
import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KeycloakServiceImpl implements IKeycloakService {

    private final Logger logger = LoggerFactory.getLogger(KeycloakServiceImpl.class);

    @Override
    public List<UserRepresentation> findAllUsers() {
        return KeycloakProvider.getRealmResource().users().list();
    }

    @Override
    public List<UserRepresentation> findUsersByUsername(String username) {
        return KeycloakProvider.getRealmResource().users().searchByUsername(username, true);
    }

    @Override
    public UserRepresentation createUser(@NonNull UserDto userDto) {
        logger.info("Creating keycloak user {}", userDto);
        int status = 0;
        UsersResource userResource = KeycloakProvider.getUserResource();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(userDto.getFirstName());
        userRepresentation.setLastName(userDto.getLastName());
        userRepresentation.setEmail(userDto.getEmail());
        userRepresentation.setUsername(userDto.getUsername());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        logger.info("Saving user {}", userRepresentation);
        
        Response response = userResource.create(userRepresentation);
        status = response.getStatus();
        //TODO handle case where no group exists
        logger.info("Keycloak user creation responded with status code {}", response.getStatus());

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
               //assing roles by client level "knowix_frontend"
                realmResource.clients().findByClientId("knowix_frontend").forEach(clientRepresentation -> {
                    List<RoleRepresentation> finalRoleRepresentations = new ArrayList<>();
                    userDto.getRoles().forEach(role -> {
                        finalRoleRepresentations.add(realmResource.clients().get(clientRepresentation.getId()).roles().get(role).toRepresentation());
                    });
                    realmResource.users().get(userId).roles().clientLevel(clientRepresentation.getId()).add(finalRoleRepresentations);
                });
            }

            return userResource.get(userId).toRepresentation();

        }else if(status == 409) {
            //TODO handle insecure password
            return null;
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(String userId) {
        KeycloakProvider.getUserResource().get(userId).remove();
    }

    @Override
    public UserRepresentation updateUser(String userId, UserDto userDto) {
        /*CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(OAuth2Constants.PASSWORD);
        credentialRepresentation.setValue(userDto.getPassword());*/

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(userDto.getFirstName());
        userRepresentation.setLastName(userDto.getLastName());
        userRepresentation.setEmail(userDto.getEmail());
        userRepresentation.setUsername(userDto.getUsername());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        //userRepresentation.setCredentials(List.of(credentialRepresentation));

        UserResource userResource = KeycloakProvider.getUserResource().get(userId);
        userResource.update(userRepresentation);

        return userResource.toRepresentation();

    }

    public UserRepresentation updateUserRoles (String userId, List<String> roles) {
        RealmResource realmResource = KeycloakProvider.getRealmResource();
        UserResource userResource = KeycloakProvider.getUserResource().get(userId);
        List<RoleRepresentation> roleRepresentations = new ArrayList<>();
        //assing roles by client level "knowix_frontend"
        realmResource.clients().findByClientId("knowix_frontend").forEach(clientRepresentation -> {
            List<RoleRepresentation> finalRoleRepresentations = new ArrayList<>();
            roles.forEach(role -> {
                finalRoleRepresentations.add(realmResource.clients().get(clientRepresentation.getId()).roles().get(role).toRepresentation());
            });
            userResource.roles().clientLevel(clientRepresentation.getId()).add(finalRoleRepresentations);
        });
        userResource.roles().realmLevel().add(roleRepresentations);
        return userResource.toRepresentation();
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
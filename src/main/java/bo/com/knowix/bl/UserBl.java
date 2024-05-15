package bo.com.knowix.bl;

import bo.com.knowix.dao.repository.KcGroupRepository;
import bo.com.knowix.dao.repository.KcUserRepository;
import bo.com.knowix.dto.UserDto;
import bo.com.knowix.entity.KcGroupEntity;
import bo.com.knowix.entity.KcUserEntity;
import bo.com.knowix.service.IKeycloakService;
import jakarta.servlet.http.HttpServletRequest;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserBl {
    private final Logger logger = LoggerFactory.getLogger(UserBl.class);

    private final KcUserRepository kcUserRepository;

    private final KcGroupRepository kcGroupRepository;

    private final IKeycloakService keycloakService;

    public UserBl(KcUserRepository kcUserRepository, IKeycloakService keycloakService, KcGroupRepository kcGroupRepository) {
        this.kcUserRepository = kcUserRepository;
        this.keycloakService = keycloakService;
        this.kcGroupRepository = kcGroupRepository;
    }

    public UserRepresentation createUser(UserDto user){
        logger.info("Creating user: " + user);
        UserRepresentation keycloackUser =  keycloakService.createUser(user);
        KcGroupEntity kcGroupEntity = kcGroupRepository.findByKcGroupId(1L);
        KcUserEntity kcUserEntity = new KcUserEntity();
        kcUserEntity.setKcUuid(keycloackUser.getId());
        kcUserEntity.setEmail(keycloackUser.getEmail());
        kcUserEntity.setFirstName(keycloackUser.getFirstName());
        kcUserEntity.setLastName(keycloackUser.getLastName());
        kcUserEntity.setStatus(true);
        kcUserEntity.setS3ProfilePicture(1);
        kcUserEntity.setKcGroup(kcGroupEntity);
        kcUserEntity.setTxDate(
                new Timestamp(new Date().getTime())
        );
        kcUserEntity.setTxHost(
                "localhost"
        );
        kcUserEntity.setTxUser(
                "admin"
        );

        KcUserEntity savedUser = kcUserRepository.save(kcUserEntity);

        return keycloackUser;
    }

    public UserRepresentation updateUser(UserDto user, String kcUuid){
        logger.info("Updating user: " + user);
        UserRepresentation keycloackUser =  keycloakService.updateUser(kcUuid, user);
        KcUserEntity kcUserEntity = kcUserRepository.findByKcUuid(kcUuid);
        kcUserEntity.setFirstName(keycloackUser.getFirstName());
        kcUserEntity.setLastName(keycloackUser.getLastName());
        kcUserEntity.setTxDate(
                new Timestamp(new Date().getTime())
        );
        kcUserEntity.setTxHost(
                "localhost"
        );
        kcUserEntity.setTxUser(
                "admin"
        );

        KcUserEntity savedUser = kcUserRepository.save(kcUserEntity);

        return keycloackUser;
    }

    public UserRepresentation updateUserRoles(UserDto user, String kcUuid){
        logger.info("Updating user roles: " + user.getRoles());
        UserRepresentation keycloackUser =  keycloakService.updateUserRoles(kcUuid, user.getRoles().stream().toList());
        return keycloackUser;
    }

    public KcUserEntity getUser(String kcUuid){
        logger.info("Getting user: " + kcUuid);
        return kcUserRepository.findByKcUuid(kcUuid);
    }

    public void blockUser(String kcUuid){
        logger.info("Blocking user: " + kcUuid);
        KcUserEntity kcUserEntity = kcUserRepository.findByKcUuid(kcUuid);
        kcUserEntity.setBlocked(true);
        kcUserRepository.save(kcUserEntity);
    }

    public void unblockUser(String kcUuid){
        logger.info("Unblocking user: " + kcUuid);
        KcUserEntity kcUserEntity = kcUserRepository.findByKcUuid(kcUuid);
        kcUserEntity.setBlocked(false);
        kcUserRepository.save(kcUserEntity);
    }

    public List<KcUserEntity> getAllUsers(){
        return kcUserRepository.findAll();
    }


}

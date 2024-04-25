package bo.com.knowix.api;

import bo.com.knowix.bl.MinioBL;
import bo.com.knowix.dao.repository.S3ObjectRepository;
import bo.com.knowix.dto.FileDto;
import bo.com.knowix.dto.NewFileDto;
import bo.com.knowix.entity.S3ObjectEntity;
import bo.com.knowix.service.IKeycloakService;
import bo.com.knowix.service.MinioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/test")
@Tag(name = "Test API", description = "API for testing purposes")
public class TestApi {

    @Autowired
    private final IKeycloakService keycloakService;

    @Autowired
    private MinioService minioService;

    @Autowired
    private S3ObjectRepository s3ObjectRepository;

    @Autowired
    private MinioBL minioBL;

    private Logger logger = LoggerFactory.getLogger(TestApi.class);

    public TestApi(IKeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @Operation(summary = "Test endpoint for educator")
    @GetMapping("/test-educator")
    public String helloEducator(@RequestHeader("X-UUID") String kcuuid){
        logger.info("Keycloak UUID: " + kcuuid);
        return "Hello Soring Boot With Keycloak -Educator";
    }

    @Operation(summary = "Test endpoint for student")
    @GetMapping("/test-student")
    public String helloStudent(@RequestHeader("X-UUID") String kcuuid)
    {
        logger.info("Keycloak UUID: " + kcuuid);
        return "Hello Soring Boot With Keycloak -Student";
    }

    @Operation(summary = "Test endpoint for everyone")
    @GetMapping("/test-no-authenticated")
    public UserRepresentation helloEveryone(@RequestHeader("X-UUID") String kcuuid){
        logger.info("Keycloak UUID: " + kcuuid);
        return keycloakService.findUserBySubject(kcuuid);
    }

    //Minio test
    @Operation(summary = "Test endpoint for uploading file")
    @PostMapping(path= "/file", consumes = {"multipart/form-data"})
    public S3ObjectEntity uploadFile(@RequestPart("file") MultipartFile file, @RequestParam("bucket") String bucketName){
        S3ObjectEntity savedFile = minioBL.uploadFile(file, bucketName);
        if(savedFile != null){
            return savedFile;
        }else{
            return null;
        }
    }

    @Operation(summary = "Test endpoint for getting file")
    @GetMapping("/file")
    public String getFile (@RequestParam String bucket, @RequestParam String fileName){
        String file = minioBL.getFile(bucket, fileName);
        if(file != null){
            return file;
        }else{
            return null;
        }
    }
}

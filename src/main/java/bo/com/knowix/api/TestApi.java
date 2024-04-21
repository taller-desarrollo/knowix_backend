package bo.com.knowix.api;

import bo.com.knowix.dao.repository.S3ObjectRepository;
import bo.com.knowix.dto.FileDto;
import bo.com.knowix.dto.NewFileDto;
import bo.com.knowix.entity.S3ObjectEntity;
import bo.com.knowix.service.IKeycloakService;
import bo.com.knowix.service.MinioService;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/test")
public class TestApi {

    @Autowired
    private final IKeycloakService keycloakService;

    @Autowired
    private MinioService minioService;

    @Autowired
    private S3ObjectRepository s3ObjectRepository;

    private Logger logger = LoggerFactory.getLogger(TestApi.class);

    public TestApi(IKeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @GetMapping("/test-educator")

    public String helloEducator(@RequestHeader("X-UUID") String kcuuid){
        logger.info("Keycloak UUID: " + kcuuid);
        return "Hello Soring Boot With Keycloak -Educator";
    }

    @GetMapping("/test-student")
    public String helloStudent(@RequestHeader("X-UUID") String kcuuid)
    {
        logger.info("Keycloak UUID: " + kcuuid);
        return "Hello Soring Boot With Keycloak -Student";
    }

    @GetMapping("/test-no-authenticated")
    public UserRepresentation helloEveryone(@RequestHeader("X-UUID") String kcuuid){
        logger.info("Keycloak UUID: " + kcuuid);
        return keycloakService.findUserBySubject(kcuuid);
    }

    //Minio test

    @PostMapping(path= "/file", consumes = {"multipart/form-data"})
    public FileDto uploadFile(@RequestPart("file") MultipartFile file, @RequestParam("bucket") String bucketName){
        try {
            logger.info("Uploading file: " + file.getOriginalFilename());
            logger.info("Bucket: " + bucketName);
            NewFileDto newFileDto = minioService.uploadFile(file, bucketName);
            S3ObjectEntity s3ObjectEntity = new S3ObjectEntity();
            s3ObjectEntity.setBucket(bucketName);
            s3ObjectEntity.setFilename(newFileDto.getFileName());
            s3ObjectEntity.setContentType(newFileDto.getContentType());
            s3ObjectEntity.setStatus(true);
            s3ObjectEntity.setTransactionDate(new java.util.Date());
            s3ObjectEntity.setTransactionUser("test");
            s3ObjectEntity.setTransactionHost("localhost");
            S3ObjectEntity savedS3Object =  s3ObjectRepository.save(s3ObjectEntity);
            return new FileDto(
                    savedS3Object.getS3ObjectId(),
                    savedS3Object.getContentType(),
                    savedS3Object.getBucket(),
                    savedS3Object.getFilename(),
                    savedS3Object.isStatus()
            );
        } catch (Exception e) {
            logger.error("Error uploading file: " + e.getMessage());
            return new FileDto(null, null, null, null, false);
        }
    }

    @GetMapping("/file")
    public String getFile (@RequestParam String bucket, @RequestParam String fileName){
        try {
            logger.info("Getting file: " + fileName);
            return minioService.getFile(bucket, fileName);
        } catch (Exception e) {
            logger.error("Error getting file: " + e.getMessage());
            return null;
        }
    }
}

package bo.com.knowix.bl;

import bo.com.knowix.dao.repository.S3ObjectRepository;
import bo.com.knowix.dto.NewFileDto;
import bo.com.knowix.entity.S3ObjectEntity;
import bo.com.knowix.service.MinioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MinioBL {

    @Autowired
    private MinioService minioService;

    @Autowired
    private S3ObjectRepository s3ObjectRepository;

    private Logger logger = LoggerFactory.getLogger(MinioBL.class);

    public MinioBL(MinioService minioService, S3ObjectRepository s3ObjectRepository) {
        this.minioService = minioService;
        this.s3ObjectRepository = s3ObjectRepository;
    }

    public S3ObjectEntity uploadFile(MultipartFile file, String bucketName) {
        try{
            logger.info("Uploading file: " + file.getOriginalFilename());
            logger.info("Bucket: " + bucketName);
            NewFileDto newFileDto = minioService.uploadFile(file, bucketName);
            S3ObjectEntity s3ObjectEntity = new S3ObjectEntity();
            s3ObjectEntity.setBucket(bucketName);
            s3ObjectEntity.setFilename(newFileDto.getFileName());
            s3ObjectEntity.setContentType(newFileDto.getContentType());
            s3ObjectEntity.setStatus(true);
            s3ObjectEntity.setTransactionDate(new java.util.Date());
            s3ObjectEntity.setTransactionUser("admin");
            s3ObjectEntity.setTransactionHost("localhost");
            logger.info("File uploaded successfully");
            return s3ObjectRepository.save(s3ObjectEntity);
        }catch (Exception e){
            logger.error("Error occurred while uploading file: " + e.getMessage());
            return null;
        }

    }

    public String getFile(String bucketName, String fileName) {
        try {
            logger.info("Getting file: " + fileName);
            logger.info("Bucket: " + bucketName);
            return minioService.getFile(bucketName, fileName);
        } catch (Exception e) {
            logger.error("Error occurred while getting file: " + e.getMessage());
            return null;
        }
    }

}

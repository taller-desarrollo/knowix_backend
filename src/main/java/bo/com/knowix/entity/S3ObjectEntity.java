package bo.com.knowix.entity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "s3_object", schema = "public")
public class S3ObjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s3_object_id_seq")
    @SequenceGenerator(name = "s3_object_id_seq", sequenceName = "s3_object_s3_object_id_seq", allocationSize = 1)
    @Column(name = "s3_object_id")
    private Long s3ObjectId;

    @Column(name = "content_type", length = 50, nullable = false)
    private String contentType;

    @Column(name = "bucket", length = 50, nullable = false)
    private String bucket;

    @Column(name = "filename", length = 100, nullable = false)
    private String filename;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_date", nullable = false)
    private Date transactionDate;

    @Column(name = "tx_user", length = 50, nullable = false)
    private String transactionUser;

    @Column(name = "tx_host", length = 50, nullable = false)
    private String transactionHost;

    public S3ObjectEntity() {
    }

    public S3ObjectEntity(Long s3ObjectId, String contentType, String bucket, String filename, boolean status, Date transactionDate, String transactionUser, String transactionHost) {
        this.s3ObjectId = s3ObjectId;
        this.contentType = contentType;
        this.bucket = bucket;
        this.filename = filename;
        this.status = status;
        this.transactionDate = transactionDate;
        this.transactionUser = transactionUser;
        this.transactionHost = transactionHost;
    }
    public Long getS3ObjectId() {
        return s3ObjectId;
    }

    public void setS3ObjectId(Long s3ObjectId) {
        this.s3ObjectId = s3ObjectId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionUser() {
        return transactionUser;
    }

    public void setTransactionUser(String transactionUser) {
        this.transactionUser = transactionUser;
    }

    public String getTransactionHost() {
        return transactionHost;
    }

    public void setTransactionHost(String transactionHost) {
        this.transactionHost = transactionHost;
    }
}


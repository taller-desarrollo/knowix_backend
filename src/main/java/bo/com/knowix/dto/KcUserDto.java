package bo.com.knowix.dto;

public class KcUserDto {
    private String kcUuid;
    private String firstName;
    private String lastName;
    private String email;
    private boolean status;
    private int s3ProfilePicture;

    private boolean isVerified;

    public KcUserDto() {
    }

    public KcUserDto(String kcUuid, String firstName, String lastName, String email, int s3ProfilePicture, boolean isVerified) {
        this.kcUuid = kcUuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.s3ProfilePicture = s3ProfilePicture;
        this.isVerified = isVerified;
    }

    public String getKcUuid() {
        return kcUuid;
    }

    public void setKcUuid(String kcUuid) {
        this.kcUuid = kcUuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getS3ProfilePicture() {
        return s3ProfilePicture;
    }

    public void setS3ProfilePicture(int s3ProfilePicture) {
        this.s3ProfilePicture = s3ProfilePicture;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }
    @Override
    public String toString() {
        return "KcUserDto{" +
                "kcUuid='" + kcUuid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", s3ProfilePicture=" + s3ProfilePicture +
                '}';
    }



}

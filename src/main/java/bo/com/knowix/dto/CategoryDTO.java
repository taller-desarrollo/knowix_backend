package bo.com.knowix.dto;

public class CategoryDTO {

    private int categoryId;
    private String categoryDescription;
    private String categoryName;
    private boolean status;

    public CategoryDTO() {
    }

    public CategoryDTO(int categoryId, String categoryDescription, String categoryName, boolean status) {
        this.categoryId = categoryId;
        this.categoryDescription = categoryDescription;
        this.categoryName = categoryName;
        this.status = status;
    }

    // Getters

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean isStatus() {
        return status;
    }

    // Setters

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // toString
    @Override
    public String toString() {
        return "CategoryDTO{" + "categoryId=" + categoryId +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", status=" + status + '}';
    }
}

package bo.com.knowix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_description", nullable = false, length = 500)
    private String categoryDescription;

    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

    @Column(name = "status", nullable = false)
    private Boolean status;

    public CategoryEntity() {
    }

    public CategoryEntity(Integer categoryId, String categoryDescription, String categoryName, Boolean status) {
        this.categoryId = categoryId;
        this.categoryDescription = categoryDescription;
        this.categoryName = categoryName;
        this.status = status;
    }

    // Getters

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Boolean getStatus() {
        return status;
    }

    // Setters

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "categoryId=" + categoryId +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", status=" + status +
                '}';
    }
}

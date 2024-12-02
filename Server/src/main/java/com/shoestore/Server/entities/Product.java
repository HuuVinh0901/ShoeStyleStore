package com.shoestore.Server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private int productID;
    @Column(name = "productName", nullable = false)
    @NotBlank(message = "Tên sản phẩm không được để trống") // Không cho phép null hoặc chuỗi rỗng
    @Size(max = 50, message = "Tên sản phẩm không được vượt quá 50 ký tự") // Giới hạn độ dài tối đa
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Tên sản phẩm chỉ được chứa chữ, số và khoảng trắng") // Chỉ cho phép chữ, số và khoảng trắng
    private String productName;
    @ElementCollection
    @CollectionTable(name = "Product_ImageURL", joinColumns = @JoinColumn(name = "productID"))
    @Column(name = "imageURL")
    private List<String> imageURL;
    @Column(name = "description", nullable = false, length = 100)
    @NotBlank(message = "Mô tả không được để trống")
    @Size(max = 100, message = "Mô tả không được vượt quá 100 ký tự")
    private String description;

    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0", inclusive = false, message = "Giá phải lớn hơn 0")
    private double price;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brandID")
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryID")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplierID")
    private Supplier supplier;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference("productDetailsReference")
    private List<ProductDetail> productDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "promotionID", nullable = true)
    @JsonIgnore
    private Promotion promotion;

    public Product(int productID, String productName, List<String> imageURL, String description, double price, String status, Brand brand, Supplier supplier, Category category, Promotion promotion, LocalDateTime createDate) {
        this.productID = productID;
        this.productName = productName;
        this.imageURL = imageURL;
        this.description = description;
        this.price = price;
        this.status = status;
        this.brand = brand;
        this.supplier = supplier;
        this.category = category;
        this.promotion = promotion;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
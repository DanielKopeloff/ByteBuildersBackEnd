package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private int stock;

    private double price;

    private String sku;

    private String picture;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(nullable=true)
    private int rating;// averaged from the comments

    @Column(name = "product_created")
    @CreationTimestamp
    private Date productCreated;

    @Column(name = "product_terminated")
    private Date productTerminated;

//    @OneToMany(
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            mappedBy="product")
//    private List<Review> reviews;

    @ManyToOne
    private Category category;
    
}

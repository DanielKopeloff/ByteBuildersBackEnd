package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private int rating;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "product_created")
    private Date productCreated;

    @Column(name = "product_terminated")
    private Date productTerminated;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private List<Review> reviews;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade =  {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="product_id")
    private List<Category> categories;

    @OneToMany(mappedBy = "product")
    private Set<ProductOrder> productOrders = new HashSet<>();

//    @ManyToMany(
//            fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable(
//            name = "product_order",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "order_id"))
//    private Set<ByteOrder> byteOrders;

}

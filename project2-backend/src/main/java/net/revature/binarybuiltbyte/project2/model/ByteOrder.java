package net.revature.binarybuiltbyte.project2.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "byte_order")
@Getter
@Setter
public class ByteOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int totalQuantity;

    private double totalPrice;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "order_created")
    @CreationTimestamp
    private Date orderCreated;

    @Column(name = "order_completed")
    @UpdateTimestamp
    private Date orderCompleted;

    @OneToMany(mappedBy = "byteOrder")
    private Set<ProductOrder> productOrders = new HashSet<>();

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "byte_user_id")
    private ByteUser byteUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy="byteOrder")
    private List<Review> reviews;

    public void add(ProductOrder productOrder) {
        if (productOrder != null) {
            if (productOrders == null) {
                productOrders = new HashSet<>();
            }
            productOrders.add(productOrder);
            productOrder.setByteOrder(this);
        }
    }

}

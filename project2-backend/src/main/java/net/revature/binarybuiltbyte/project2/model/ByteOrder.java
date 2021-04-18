package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "byte_order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ByteOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private ByteUser byteUser;

    @Column(name = "order_created")
    @CreationTimestamp
    private Date orderCreated;

    @Column(name = "order_completed")
    private Date orderCompleted;

    @OneToMany(mappedBy = "byteOrder")
    private Set<ProductOrder> productOrders = new HashSet<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy="byteOrder")
    private List<Review> reviews;

}

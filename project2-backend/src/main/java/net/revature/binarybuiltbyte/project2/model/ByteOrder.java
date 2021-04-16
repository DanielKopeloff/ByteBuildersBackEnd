package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.revature.binarybuiltbyte.project2.model.ByteUser;
import net.revature.binarybuiltbyte.project2.model.ProductOrder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
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
    @JoinColumn(
            name = "user_id")
    private ByteUser byteUser;

    @Column(name = "order_created")
    @CreationTimestamp
    private Date orderCreated;

    @Column(name = "order_completed")
    private Date orderCompleted;

    @OneToMany(mappedBy = "byteOrder")
    private Set<ProductOrder> productOrders = new HashSet<>();

}

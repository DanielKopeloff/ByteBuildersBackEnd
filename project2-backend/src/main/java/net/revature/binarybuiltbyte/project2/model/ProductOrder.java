package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    /**
     * For these I think it should be one to one because for each product inside the order we want the
     * Quantity to be about just that one product
     */
    @OneToOne(cascade = CascadeType.PERSIST)
    private ByteOrder order;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Product product;

    /**
     * Not sure that we need this or maybe im misunderstanding how this is working
     * From my understanding it is just pointing to one product in one order
     * Or are you trying to point to multiple products in one order
     * I think the issue is here im not sure what OrderProduct Does becuase
     * I thought it would do this
     */
//    @OneToMany(
//            mappedBy = "order",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<OrderProduct> products = new ArrayList<>();

}

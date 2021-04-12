package net.revature.binarybuiltbyte.project2.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "OrderProduct")
@Table(name = "order_product")
public class OrderProduct {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)// should try this out after trying with all Identity
    private OrderProductId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @MapsId("orderId")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @MapsId("productId")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    private OrderProduct(){}

    public OrderProduct(Order order, Product product) {
        this.order = order;
        this.product = product;
        this.id = new OrderProductId(order.getId(), product.getId());
    }

    public OrderProduct(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderProduct(OrderProductId id, Order order, Product product, int quantity) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderProductId getId() {
        return id;
    }

    public void setId(OrderProductId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrderProduct that = (OrderProduct) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}

package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Entity(name = "Product")
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "stock")
    private int stock;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name = "price")
    private double price;

    @OneToMany(mappedBy = "productId")
    private Set<Review> review;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderProduct> orders = new ArrayList<>();

    public void addOrder(Order order){
        OrderProduct orderProduct = new OrderProduct(order, this);
        orders.add(orderProduct);
        order.getProducts().add(orderProduct);
    }

    public void removeOrder(Order order) {
        for (Iterator<OrderProduct> iterator = orders.iterator();
             iterator.hasNext(); ) {
            OrderProduct orderProduct = iterator.next();

            if (orderProduct.getProduct().equals(this) &&
                    orderProduct.getOrder().equals(order)) {
                iterator.remove();
                orderProduct.getOrder().getProducts().remove(orderProduct);
                orderProduct.setProduct(null);
                orderProduct.setOrder(null);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategories() {
        return categoryId;
    }

    public void setCategories(Category categories) {
        this.categoryId = categories;
    }

    public List<OrderProduct> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderProduct> orders) {
        this.orders = orders;
    }
}

package model;

import enums.OrderStatus;
import persistence.utils.Column;
import persistence.utils.Entity;
import persistence.utils.Id;
import persistence.utils.Table;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Table(name = "order")
public class Order implements Entity<Integer> {

    @Id
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "user_id")
    private User user;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "status")
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "product_id")
    private Product product;

    @Override
    public String toString() {
        return id + " - " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(orderDate) + " - " + status.getStatus();
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

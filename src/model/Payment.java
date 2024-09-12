package model;

import enums.PaymentMethod;
import persistence.utils.Column;
import persistence.utils.Entity;
import persistence.utils.Id;
import persistence.utils.Table;

import java.math.BigDecimal;
import java.sql.Date;

@Table(name = "payment")
public class Payment implements Entity<Integer> {

    @Id
    @Column(name = "payment_id")
    private Integer id;

    @Column(name = "order_id")
    private Order order;

    @Column(name = "date")
    private Date date;

    @Column(name = "method")
    private PaymentMethod method;

    @Column(name = "amount")
    private BigDecimal amount;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

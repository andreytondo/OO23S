package model;

import enums.ShippingStatus;
import persistence.utils.Column;
import persistence.utils.Entity;
import persistence.utils.Id;
import persistence.utils.Table;

@Table(name = "shipping")
public class Shipping implements Entity<Integer> {

    @Id
    @Column(name = "shipping_id")
    private Integer id;

    @Column(name = "order_id")
    private Order order;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "cep")
    private String cep;

    @Column(name = "state_id")
    private State state;

    @Column(name = "status")
    private ShippingStatus status;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ShippingStatus getStatus() {
        return status;
    }

    public void setStatus(ShippingStatus status) {
        this.status = status;
    }
}

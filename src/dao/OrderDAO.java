package dao;

import model.Order;
import persistence.AbstractDAO;

public class OrderDAO extends AbstractDAO<Integer, Order> {

    public OrderDAO() {
        super(Order.class);
    }
}

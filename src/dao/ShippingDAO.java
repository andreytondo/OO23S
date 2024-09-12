package dao;

import model.Shipping;
import persistence.AbstractDAO;

public class ShippingDAO extends AbstractDAO<Integer, Shipping> {

    public ShippingDAO() {
        super(Shipping.class);
    }
}

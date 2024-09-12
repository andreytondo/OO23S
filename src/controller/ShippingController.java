package controller;

import dao.ShippingDAO;
import dao.StateDAO;
import model.Shipping;
import persistence.AbstractDAO;

import java.util.List;

public class ShippingController implements Controller<Integer, Shipping> {

    @Override
    public AbstractDAO<Integer, Shipping> getDAO() {
        return new ShippingDAO();
    }

    @Override
    public List<Shipping> loadData() {
        List<Shipping> shippings = getDAO().getAll();

        for (Shipping shipping : shippings) {
            shipping.setState(new StateDAO().getById(shipping.getState().getId()));
        }

        return shippings;
    }

    @Override
    public void create(Shipping entity) {
        getDAO().create(entity);
    }

    @Override
    public void update(Shipping entity) {
        getDAO().update(entity);
    }

    @Override
    public void delete(Shipping entity) {
        getDAO().delete(entity);
    }
}

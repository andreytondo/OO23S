package controller;

import dao.PaymentDAO;
import model.Payment;
import persistence.AbstractDAO;

import java.util.List;

public class PaymentController implements Controller<Integer, Payment> {

    @Override
    public AbstractDAO<Integer, Payment> getDAO() {
        return new PaymentDAO();
    }

    @Override
    public List<Payment> loadData() {
        return getDAO().getAll();
    }

    @Override
    public void create(Payment entity) {
        getDAO().create(entity);
    }

    @Override
    public void update(Payment entity) {
        getDAO().update(entity);
    }

    @Override
    public void delete(Payment entity) {
        getDAO().delete(entity);
    }
}

package dao;

import model.Payment;
import persistence.AbstractDAO;

public class PaymentDAO extends AbstractDAO<Integer, Payment> {

    public PaymentDAO() {
        super(Payment.class);
    }
}

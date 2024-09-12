package view;

import controller.Controller;
import controller.PaymentController;
import dao.OrderDAO;
import enums.PaymentMethod;
import model.Order;
import model.Payment;
import utils.FieldUtils;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class PaymentFrame extends CrudFrame<Integer, Payment> {

    JComboBox<Order> orderField;
    JFormattedTextField dateField;
    JComboBox<PaymentMethod> methodField;
    JFormattedTextField amountField;

    public PaymentFrame() {
        super("Controle de Pagamentos");
        setLayout(new BorderLayout());
    }

    @Override
    protected Object[] mapEntityToRow(Payment entity) {
        return new Object[]{
                entity.getId(),
                new SimpleDateFormat("dd/MM/yyyy").format(entity.getDate()),
                entity.getMethod(),
                entity.getAmount()
        };
    }

    @Override
    protected void buildForm(JPanel formPanel) {
        formPanel.add(new JLabel("Pedido:"));
        orderField = new JComboBox<>(new OrderDAO().getAll().toArray(new Order[0]));
        formPanel.add(orderField);

        formPanel.add(new JLabel("Data:"));
        dateField = new JFormattedTextField(FieldUtils.createDateMask());
        formPanel.add(dateField);

        formPanel.add(new JLabel("Método:"));
        methodField = new JComboBox<>(PaymentMethod.values());
        formPanel.add(methodField);

        formPanel.add(new JLabel("Total:"));
        amountField = new JFormattedTextField(FieldUtils.createPriceMask());
        formPanel.add(amountField);

        add(formPanel, BorderLayout.CENTER);
    }

    @Override
    protected void insert() {
        Payment payment = new Payment();
        payment.setOrder((Order) orderField.getSelectedItem());
        payment.setDate(FieldUtils.parseDate(dateField.getText()));
        payment.setMethod((PaymentMethod) methodField.getSelectedItem());
        payment.setAmount(FieldUtils.parsePrice(amountField.getText()));
        getController().create(payment);
        loadData();
    }

    @Override
    protected void update() {
        Payment payment = getSelectedEntity();
        payment.setOrder((Order) orderField.getSelectedItem());
        payment.setDate(FieldUtils.parseDate(dateField.getText()));
        payment.setMethod((PaymentMethod) methodField.getSelectedItem());
        payment.setAmount(FieldUtils.parsePrice(amountField.getText()));
        getController().update(payment);
        loadData();
    }

    @Override
    protected Controller<Integer, Payment> getController() {
        return new PaymentController();
    }

    @Override
    protected Object[] getColumns() {
        return new Object[]{
                "ID",
                "Data",
                "Método",
                "Total"
        };
    }
}

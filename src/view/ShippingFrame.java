package view;

import controller.Controller;
import controller.ShippingController;
import dao.OrderDAO;
import dao.StateDAO;
import enums.ShippingStatus;
import model.Order;
import model.Shipping;
import model.State;

import javax.swing.*;
import java.awt.*;

public class ShippingFrame extends CrudFrame<Integer, Shipping> {

    JComboBox<Order> orderField;
    JTextField addressField;
    JTextField cityField;
    JTextField cepField;
    JComboBox<State> stateField;
    JComboBox<ShippingStatus> statusField;

    public ShippingFrame() {
        super("Controle de Entregas");
        setLayout(new BorderLayout());
    }

    @Override
    protected Object[] mapEntityToRow(Shipping entity) {
        return new Object[]{
                entity.getId(),
                entity.getOrder().getId(),
                entity.getAddress(),
                entity.getCity(),
                entity.getCep(),
                entity.getState(),
                entity.getStatus()
        };
    }

    @Override
    protected void buildForm(JPanel formPanel) {
        formPanel.add(new JLabel("Pedido:"));
        orderField = new JComboBox<>(new OrderDAO().getAll().toArray(new Order[0]));
        formPanel.add(orderField);

        formPanel.add(new JLabel("Endereço:"));
        addressField = new JTextField();
        formPanel.add(addressField);

        formPanel.add(new JLabel("Cidade:"));
        cityField = new JTextField();
        formPanel.add(cityField);

        formPanel.add(new JLabel("CEP:"));
        cepField = new JTextField();
        formPanel.add(cepField);

        formPanel.add(new JLabel("Estado:"));
        stateField = new JComboBox<>(new StateDAO().getAll().toArray(new State[0]));
        formPanel.add(stateField);

        formPanel.add(new JLabel("Status:"));
        statusField = new JComboBox<>(ShippingStatus.values());
        formPanel.add(statusField);

        add(formPanel);
    }

    @Override
    protected void insert() {
        Shipping shipping = new Shipping();
        shipping.setOrder((Order) orderField.getSelectedItem());
        shipping.setAddress(addressField.getText());
        shipping.setCity(cityField.getText());
        shipping.setCep(cepField.getText());
        shipping.setState((State) stateField.getSelectedItem());
        shipping.setStatus((ShippingStatus) statusField.getSelectedItem());
        getController().create(shipping);
        loadData();
    }

    @Override
    protected void update() {
        Shipping shipping = new Shipping();
        shipping.setId(getSelectedEntity().getId());
        shipping.setOrder((Order) orderField.getSelectedItem());
        shipping.setAddress(addressField.getText());
        shipping.setCity(cityField.getText());
        shipping.setCep(cepField.getText());
        shipping.setState((State) stateField.getSelectedItem());
        shipping.setStatus((ShippingStatus) statusField.getSelectedItem());
        getController().update(shipping);
        loadData();
    }

    @Override
    protected Controller<Integer, Shipping> getController() {
        return new ShippingController();
    }

    @Override
    protected Object[] getColumns() {
        return new Object[]{
                "ID",
                "Pedido",
                "Endereço",
                "Cidade",
                "CEP",
                "Estado",
                "Status"
        };
    }
}

package view;

import controller.Controller;
import controller.OrderController;
import dao.ProductDAO;
import dao.UserDAO;
import enums.OrderStatus;
import model.Order;
import model.Product;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class OrderFrame extends CrudFrame<Integer, Order> {

    JComboBox<User> userField;
    JComboBox<OrderStatus> statusField;
    JComboBox<Product> productsField;

    public OrderFrame() {
        super("Controle de Pedidos");
        setLayout(new BorderLayout());
    }

    @Override
    protected Object[] mapEntityToRow(Order entity) {
        return new Object[]{
                entity.getId(),
                entity.getUser().getName(),
                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(entity.getOrderDate()),
                entity.getStatus(),
                entity.getProduct().getName()
        };
    }

    @Override
    protected void buildForm(JPanel formPanel) {
        formPanel.add(new JLabel("Usuário:"));
        userField = new JComboBox<>(new UserDAO().getAll().toArray(new User[0]));
        formPanel.add(userField);

        formPanel.add(new JLabel("Status:"));
        statusField = new JComboBox<>(OrderStatus.values());
        formPanel.add(statusField);

        formPanel.add(new JLabel("Produto:"));
        productsField = new JComboBox<>(new ProductDAO().getAll().toArray(new Product[0]));
        formPanel.add(productsField);

        add(formPanel, BorderLayout.CENTER);
    }

    @Override
    protected void insert() {
        Order order = new Order();
        order.setUser((User) userField.getSelectedItem());
        order.setStatus((OrderStatus) statusField.getSelectedItem());
        order.setProduct((Product) productsField.getSelectedItem());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        getController().create(order);
        loadData();
    }

    @Override
    protected void update() {
        Order order = new Order();
        order.setId(getSelectedEntity().getId());
        order.setUser((User) userField.getSelectedItem());
        order.setStatus((OrderStatus) statusField.getSelectedItem());
        order.setProduct((Product) productsField.getSelectedItem());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        getController().update(order);
        loadData();
    }

    @Override
    protected Controller<Integer, Order> getController() {
        return new OrderController();
    }

    @Override
    protected Object[] getColumns() {
        return new Object[]{
                "ID",
                "Usuário",
                "Data",
                "Status",
                "Produto"
        };
    }
}

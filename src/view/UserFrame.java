package view;

import controller.Controller;
import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class UserFrame extends CrudFrame<Integer, User> {

    JTextField usernameField;
    JTextField emailField;
    JPasswordField passwordField;
    JTextField nameField;

    public UserFrame() {
        super("Manage Users");
        setLayout(new BorderLayout());
    }

    @Override
    protected Object[] mapEntityToRow(User entity) {
        return new Object[]{
                entity.getId(),
                entity.getName(),
                entity.getUsername(),
                entity.getEmail()
        };
    }

    @Override
    protected void buildForm(JPanel formPanel) {
        formPanel.add(new JLabel("Nome:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Login:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Senha:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        add(formPanel, BorderLayout.CENTER);
    }

    @Override
    protected Controller<Integer, User> getController() {
        return new UserController();
    }

    @Override
    protected Object[] getColumns() {
        return new Object[]{"ID", "Nome", "Login", "Email"};
    }

    @Override
    protected void insert() {
        User user = new User();
        user.setUsername(usernameField.getText());
        user.setEmail(emailField.getText());
        user.setPassword(new String(passwordField.getPassword()));
        user.setName(nameField.getText());

        getController().create(user);
        loadData();
    }

    @Override
    protected void update() {
        User user = new User();
        user.setId(getSelectedEntity().getId());
        user.setUsername(usernameField.getText());
        user.setEmail(emailField.getText());
        user.setName(nameField.getText());
        user.setPassword(new String(passwordField.getPassword()));

        getController().update(user);
        loadData();
    }
}

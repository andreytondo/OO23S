package view;

import controller.Controller;
import controller.ProductController;
import model.Product;
import utils.FieldUtils;

import javax.swing.*;
import java.awt.*;

public class ProductFrame extends CrudFrame<Integer, Product> {

    JTextField nameField;
    JTextField descriptionField;
    JFormattedTextField priceField;
    JTextField stockField;

    public ProductFrame() {
        super("Controle de Produtos");
        setLayout(new BorderLayout());
    }

    @Override
    protected Object[] mapEntityToRow(Product entity) {
        return new Object[]{
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock()
        };
    }

    @Override
    protected void buildForm(JPanel formPanel) {
        formPanel.add(new JLabel("Nome:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Descrição:"));
        descriptionField = new JTextField();
        formPanel.add(descriptionField);

        formPanel.add(new JLabel("Preço:"));
        priceField = new JFormattedTextField(FieldUtils.createPriceMask());
        formPanel.add(priceField);

        formPanel.add(new JLabel("Quantidade:"));
        stockField = new JTextField();
        formPanel.add(stockField);

        add(formPanel, BorderLayout.CENTER);
    }

    @Override
    protected void insert() {
        Product product = new Product();
        product.setName(nameField.getText());
        product.setDescription(descriptionField.getText());
        product.setPrice(FieldUtils.parsePrice(priceField.getText()));
        product.setStock(Integer.parseInt(stockField.getText()));

        getController().create(product);
        loadData();
    }

    @Override
    protected void update() {
        Product product = new Product();
        product.setId(getSelectedEntity().getId());
        product.setName(nameField.getText());
        product.setDescription(descriptionField.getText());
        product.setPrice(FieldUtils.parsePrice(priceField.getText()));
        product.setStock(Integer.parseInt(stockField.getText()));

        getController().update(product);
        loadData();
    }

    @Override
    protected Controller<Integer, Product> getController() {
        return new ProductController();
    }

    @Override
    protected Object[] getColumns() {
        return new Object[]{"ID", "Nome", "Descrição", "Preço", "Quantidade"};
    }
}

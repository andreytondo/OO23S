package controller;

import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;
import model.Order;
import persistence.AbstractDAO;

import java.util.List;

public class OrderController implements Controller<Integer, Order> {

    @Override
    public AbstractDAO<Integer, Order> getDAO() {
        return new OrderDAO();
    }

    @Override
    public List<Order> loadData() {
        UserDAO userDAO = new UserDAO();
        ProductDAO productDAO = new ProductDAO();
        List<Order> orders = getDAO().getAll();

        for (Order order : orders) {
            order.setUser(userDAO.getById(order.getUser().getId()));
            order.setProduct(productDAO.getById(order.getProduct().getId()));
        }

        return orders;
    }

    @Override
    public void create(Order entity) {
        getDAO().create(entity);
        updateProductStock(entity, false);
    }

    @Override
    public void update(Order entity) {
        getDAO().update(entity);
    }

    @Override
    public void delete(Order entity) {

        getDAO().delete(entity);
        updateProductStock(entity, true);
    }

    void updateProductStock(Order order, boolean isDelete) {
        Integer newStock = order.getProduct().getStock() + (isDelete ? 1 : -1);

        ProductController productController = new ProductController();
        productController.updateStock(order.getProduct(), newStock);
    }
}

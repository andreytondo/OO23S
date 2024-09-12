package controller;

import dao.ProductDAO;
import model.Product;
import persistence.AbstractDAO;

import java.util.List;

public class ProductController implements Controller<Integer, Product> {

    @Override
    public AbstractDAO<Integer, Product> getDAO() {
        return new ProductDAO();
    }

    @Override
    public List<Product> loadData() {
        return getDAO().getAll();
    }

    @Override
    public void create(Product entity) {
        getDAO().create(entity);
    }

    @Override
    public void update(Product entity) {
        getDAO().update(entity);
    }

    @Override
    public void delete(Product entity) {
        getDAO().delete(entity);
    }

    public void updateStock(Product product, Integer stock) {
        getDAO().executeSql("UPDATE product SET stock = ? WHERE product_id = ?", stock, product.getId());
    }
}

package dao;

import model.Product;
import persistence.AbstractDAO;

public class ProductDAO extends AbstractDAO<Integer, Product> {

    public ProductDAO() {
        super(Product.class);
    }
}
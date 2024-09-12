package controller;

import dao.UserDAO;
import model.User;
import persistence.AbstractDAO;

import java.util.List;

public class UserController implements Controller<Integer, User> {

    @Override
    public AbstractDAO<Integer, User> getDAO() {
        return new UserDAO();
    }

    @Override
    public List<User> loadData() {
        return getDAO().getAll();
    }

    @Override
    public void create(User entity) {
        getDAO().create(entity);
    }

    @Override
    public void update(User entity) {
        getDAO().update(entity);
    }

    @Override
    public void delete(User entity) {
        getDAO().delete(entity);
    }
}

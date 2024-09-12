package dao;

import model.User;
import persistence.AbstractDAO;

public class UserDAO extends AbstractDAO<Integer, User> {

    public UserDAO() {
        super(User.class);
    }
}

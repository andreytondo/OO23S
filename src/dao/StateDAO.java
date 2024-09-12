package dao;

import model.State;
import persistence.AbstractDAO;

public class StateDAO extends AbstractDAO<Integer, State> {

    public StateDAO() {
        super(State.class);
    }
}

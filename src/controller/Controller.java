package controller;

import persistence.AbstractDAO;
import persistence.utils.Entity;

import java.util.List;

public interface Controller<T, E extends Entity<T>> {

    AbstractDAO<T, E> getDAO();

    List<E> loadData();

    void create(E entity);

    void update(E entity);

    void delete(E entity);
}

package persistence.utils;

public interface Entity<T> {

    T getId();

    void setId(T value);
}

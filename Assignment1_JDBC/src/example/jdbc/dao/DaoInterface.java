package example.jdbc.dao;

import java.sql.SQLException;
import java.util.Collection;

public interface DaoInterface<T, K> {
    Collection<T> retrieveAll() throws SQLException;
    T retrieveOne(K key);
    void create(T t);
    void update(T t);
    void delete(K key);
}

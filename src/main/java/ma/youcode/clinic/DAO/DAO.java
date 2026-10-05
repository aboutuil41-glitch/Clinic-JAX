package ma.youcode.clinic.DAO;

import java.util.Optional;

public interface DAO<T> {
    void save(T entity);
    Optional<T> findById(int id);
    void delete(int id);
}

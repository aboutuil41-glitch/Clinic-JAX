package ma.youcode.clinic.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDao<T> implements DAO<T> {
    protected Connection getConnection() throws SQLException{
        return DBconnection.getConnection();
    }
}

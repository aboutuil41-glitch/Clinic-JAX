package ma.youcode.clinic.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.github.cdimascio.dotenv.Dotenv;

public class DBconnection {

    private static final Dotenv dotenv = Dotenv.load();
    private static final HikariDataSource dataSource;

    static {
    HikariConfig config = new HikariConfig();

    config.setJdbcUrl(dotenv.get("DB_URL"));
    config.setUsername(dotenv.get("DB_USER"));
    config.setPassword(dotenv.get("DB_PASSWORD"));
    config.setMaximumPoolSize(10);

    try {
        System.out.println("MYSQL DRIVER: " + Class.forName("com.mysql.cj.jdbc.Driver"));
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    dataSource = new HikariDataSource(config);
    }


    private DBconnection() {}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
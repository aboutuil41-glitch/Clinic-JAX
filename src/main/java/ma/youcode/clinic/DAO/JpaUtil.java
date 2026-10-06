package ma.youcode.clinic.DAO;

import java.util.HashMap;
import java.util.Map;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    private static final EntityManagerFactory emf;

    static {
        Dotenv dotenv = Dotenv.load();

        Map<String, String> props = new HashMap<>();
        props.put("jakarta.persistence.jdbc.url", dotenv.get("DB_URL"));
        props.put("jakarta.persistence.jdbc.user", dotenv.get("DB_USER"));
        props.put("jakarta.persistence.jdbc.password", dotenv.get("DB_PASSWORD"));
        props.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");

        emf = Persistence.createEntityManagerFactory("clinicPU", props);
    }

    private JpaUtil() { }

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
}
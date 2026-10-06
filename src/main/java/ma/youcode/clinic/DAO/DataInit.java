package ma.youcode.clinic.DAO;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataInit {

    public static void init() {
        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // ---- Brief 1 tables (unchanged) ----
            stmt.execute("CREATE TABLE IF NOT EXISTS users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100), "
                    + "email VARCHAR(100), "
                    + "password VARCHAR(255), "
                    + "role VARCHAR(20))");

            stmt.execute("CREATE TABLE IF NOT EXISTS patients ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100), "
                    + "firstname VARCHAR(100), "
                    + "birth_date DATE, "
                    + "number VARCHAR(20), "
                    + "blood_pressure VARCHAR(20), "
                    + "heart_rate INT, "
                    + "temperature DOUBLE, "
                    + "respiratory_rate INT, "
                    + "arrival_time DATETIME)");

            stmt.execute("CREATE TABLE IF NOT EXISTS consultations ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "patient_id INT, "
                    + "doctor_id INT, "
                    + "date DATETIME, "
                    + "status VARCHAR(20), "
                    + "reason VARCHAR(250), "
                    + "observation VARCHAR(250), "
                    + "diagnosis VARCHAR(250), "
                    + "treatment VARCHAR(250), "
                    + "cost DOUBLE, "
                    + "FOREIGN KEY (patient_id) REFERENCES patients(id), "
                    + "FOREIGN KEY (doctor_id) REFERENCES users(id))");

            // ---- Brief 2 tables (names follow the Java models) ----
            stmt.execute("CREATE TABLE IF NOT EXISTS specialists ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "user_id INT NOT NULL UNIQUE, "
                    + "rate INT NOT NULL, "
                    + "role VARCHAR(30) NOT NULL, "
                    + "FOREIGN KEY (user_id) REFERENCES users(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS demande_expertis ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "consultation_id INT NOT NULL, "
                    + "specialist_id INT NOT NULL, "
                    + "question TEXT NOT NULL, "
                    + "priority VARCHAR(20) NOT NULL, "
                    + "status VARCHAR(20) NOT NULL DEFAULT 'Pending', "
                    + "opinion TEXT, "
                    + "reccomendation TEXT, "
                    + "creation DATETIME DEFAULT CURRENT_TIMESTAMP, "
                    + "FOREIGN KEY (consultation_id) REFERENCES consultations(id), "
                    + "FOREIGN KEY (specialist_id) REFERENCES specialists(id))");

            // ---- Test accounts: 1 generalist, 2 specialists ----
            ensureUser(conn, "Dr. Alami", "generaliste@clinic.ma", "generaliste123", "Doctor");
            int cardioUserId = ensureUser(conn, "Dr. Benani", "cardio@clinic.ma", "specialiste123", "SPECIALISTE");
            int pneumoUserId = ensureUser(conn, "Dr. Chraibi", "pneumo@clinic.ma", "specialiste123", "SPECIALISTE");

            ensureSpecialist(conn, cardioUserId, 300, "CARDIOLOGIE");
            ensureSpecialist(conn, pneumoUserId, 250, "PNEUMOLOGIE");

            System.out.println("Database ready.");

        } catch (Exception e) {
            System.out.println("Creation Error : " + e.getMessage());
        }
    }

    private static int ensureUser(Connection conn, String name, String email,
                                  String plainPassword, String role) throws SQLException {
        try (PreparedStatement select = conn.prepareStatement("SELECT id FROM users WHERE email = ?")) {
            select.setString(1, email);
            try (ResultSet rs = select.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }
        try (PreparedStatement insert = conn.prepareStatement(
                "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            insert.setString(1, name);
            insert.setString(2, email);
            insert.setString(3, BCrypt.hashpw(plainPassword, BCrypt.gensalt()));
            insert.setString(4, role);
            insert.executeUpdate();
            try (ResultSet keys = insert.getGeneratedKeys()) {
                keys.next();
                return keys.getInt(1);
            }
        }
    }

    private static void ensureSpecialist(Connection conn, int userId, int rate, String role) throws SQLException {
        try (PreparedStatement select = conn.prepareStatement("SELECT id FROM specialists WHERE user_id = ?")) {
            select.setInt(1, userId);
            try (ResultSet rs = select.executeQuery()) {
                if (rs.next()) return;
            }
        }
        try (PreparedStatement insert = conn.prepareStatement(
                "INSERT INTO specialists (user_id, rate, role) VALUES (?, ?, ?)")) {
            insert.setInt(1, userId);
            insert.setInt(2, rate);
            insert.setString(3, role);
            insert.executeUpdate();
        }
    }
} 
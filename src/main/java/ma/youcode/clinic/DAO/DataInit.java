package ma.youcode.clinic.DAO;

import java.sql.Connection;
import java.sql.Statement;


public class DataInit {
    public static void init(){
        try(Connection conn = DBconnection.getConnection();
         Statement stmt = conn.createStatement())
         {
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

            System.out.println("Database ready.");

        }
        catch(Exception e){
            System.out.println("Creation Error : " + e.getMessage());
        }
    }
    
}
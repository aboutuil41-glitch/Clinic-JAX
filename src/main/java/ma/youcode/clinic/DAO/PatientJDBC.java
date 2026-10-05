package ma.youcode.clinic.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ma.youcode.clinic.Models.Patient;

public class PatientJDBC extends AbstractDao<Patient> implements PatientDao {

    @Override
    public void save(Patient patient) {
        String prmt = "INSERT INTO `patients`(`name`, `firstname`, `birth_date`, `number`, `blood_pressure`, `heart_rate`, `temperature`, `respiratory_rate`, `arrival_time`) VALUES (?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getFirstname());
            stmt.setDate(3, Date.valueOf(patient.getBirthDate()));
            stmt.setString(4, patient.getNumber());
            stmt.setString(5, patient.getBloodPressure());
            stmt.setInt(6, patient.getHeartRate());
            stmt.setDouble(7, patient.getTemperature());
            stmt.setInt(8, patient.getRespiratoryRate());
            stmt.setTimestamp(9, Timestamp.valueOf(patient.getArrivalTime()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Patient> findById(int id) {
        String prmt = "SELECT * FROM patients WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Patient(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("firstname"),
                    rs.getDate("birth_date").toLocalDate(),
                    rs.getString("number"),
                    rs.getString("blood_pressure"),
                    rs.getInt("heart_rate"),
                    rs.getDouble("temperature"),
                    rs.getInt("respiratory_rate"),
                    rs.getTimestamp("arrival_time").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Patient> findByNumber(String number) {
        String prmt = "SELECT * FROM patients WHERE number = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            stmt.setString(1, number);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Patient(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("firstname"),
                    rs.getDate("birth_date").toLocalDate(),
                    rs.getString("number"),
                    rs.getString("blood_pressure"),
                    rs.getInt("heart_rate"),
                    rs.getDouble("temperature"),
                    rs.getInt("respiratory_rate"),
                    rs.getTimestamp("arrival_time").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> result = new ArrayList<>();

        String prmt = "SELECT * FROM patients";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(new Patient(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("firstname"),
                    rs.getDate("birth_date").toLocalDate(),
                    rs.getString("number"),
                    rs.getString("blood_pressure"),
                    rs.getInt("heart_rate"),
                    rs.getDouble("temperature"),
                    rs.getInt("respiratory_rate"),
                    rs.getTimestamp("arrival_time").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public void delete(int id) {
        String prmt = "DELETE FROM patients WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
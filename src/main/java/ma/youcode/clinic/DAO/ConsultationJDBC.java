package ma.youcode.clinic.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ma.youcode.clinic.Models.Consultation;

public class ConsultationJDBC extends AbstractDao<Consultation> implements ConsultationDao {

    @Override
    public void save(Consultation consultation) {
        String prmt = "INSERT INTO `consultations`(`patient_id`, `doctor_id`, `date`, `status`, `reason`, `observation`, `diagnosis`, `treatment`, `cost`) VALUES (?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            stmt.setInt(1, consultation.getPatientId());
            stmt.setInt(2, consultation.getDoctorId());
            stmt.setTimestamp(3, Timestamp.valueOf(consultation.getTime()));
            stmt.setString(4, consultation.getStatue().name());
            stmt.setString(5, consultation.getReason());
            stmt.setString(6, consultation.getObservation());
            stmt.setString(7, consultation.getDiagnosis());
            stmt.setString(8, consultation.getTreatment());
            stmt.setDouble(9, consultation.getCost());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Consultation> findById(int id) {
        String prmt = "SELECT * FROM consultations WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Consultation(
                    rs.getInt("id"),
                    rs.getString("reason"),
                    Consultation.Status.valueOf(rs.getString("status")),
                    rs.getString("observation"),
                    rs.getString("diagnosis"),
                    rs.getString("treatment"),
                    rs.getDouble("cost"),
                    rs.getTimestamp("date").toLocalDateTime(),
                    rs.getInt("patient_id"),
                    rs.getInt("doctor_id")
                )); 
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Consultation> getAll() {
        List<Consultation> result = new ArrayList<>();

        String prmt = "SELECT * FROM consultations";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(new Consultation(
                    rs.getInt("id"),
                    rs.getString("reason"),
                    Consultation.Status.valueOf(rs.getString("status")),
                    rs.getString("observation"),
                    rs.getString("diagnosis"),
                    rs.getString("treatment"),
                    rs.getDouble("cost"),
                    rs.getTimestamp("date").toLocalDateTime(),
                    rs.getInt("patient_id"),
                    rs.getInt("doctor_id")
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public void delete(int id) {
        String prmt = "DELETE FROM consultations WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
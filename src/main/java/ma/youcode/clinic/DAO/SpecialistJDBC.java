package ma.youcode.clinic.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ma.youcode.clinic.Models.Specialist;

public class SpecialistJDBC extends AbstractDao<Specialist> implements SpecialistDao {

    @Override
    public List<Specialist> findAll() {
        List<Specialist> list = new ArrayList<>();
        String prmt = "SELECT * FROM specialiste";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Specialist(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("rate"),
                    Specialist.SpecialistList.valueOf(rs.getString("role"))
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    @Override
    public void save(Specialist entity) { }

    @Override
    public Optional<Specialist> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void delete(int id) { }
}
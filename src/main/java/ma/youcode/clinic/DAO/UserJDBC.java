package ma.youcode.clinic.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import ma.youcode.clinic.Models.User;

public class UserJDBC extends AbstractDao<User> implements UserDao {

    @Override
    public void save(User user) {
        String prmt = "INSERT INTO `users`(`name`, `email`, `password`, `role`) VALUES (?,?,?,?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<User> findById(int id) {
        String prmt = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    User.UserRole.valueOf(rs.getString("role"))
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String prmt = "SELECT * FROM users WHERE email = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    User.UserRole.valueOf(rs.getString("role"))
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public void delete(int id) {
        String prmt = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(prmt)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
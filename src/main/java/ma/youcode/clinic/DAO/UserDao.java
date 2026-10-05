package ma.youcode.clinic.DAO;

import java.util.Optional;

import ma.youcode.clinic.Models.User;

public interface UserDao extends DAO<User> {

    Optional<User> findByEmail(String email);
}
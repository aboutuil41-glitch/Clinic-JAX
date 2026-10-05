package ma.youcode.clinic.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import ma.youcode.clinic.DAO.UserDao;
import ma.youcode.clinic.Models.User;

public class AuthService {

    private final UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User Login(String email, String password) {

        Optional<User> result = userDao.findByEmail(email);

        if (result.isEmpty()) {
            return null;
        }

        User user = result.get();

        if (BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }

        return null;
    }
}


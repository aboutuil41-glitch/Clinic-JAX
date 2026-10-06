package ma.youcode.clinic;

import ma.youcode.clinic.DAO.DataInit;
import ma.youcode.clinic.DAO.UserDao;
import ma.youcode.clinic.DAO.UserJDBC;
import ma.youcode.clinic.Models.User;
import org.mindrot.jbcrypt.BCrypt;

public class Main {

    public static void main(String[] args) {

        DataInit.init();

        // String password = BCrypt.hashpw("1234", BCrypt.gensalt(12));

        // UserJDBC userDao = new UserJDBC();

        // userDao.save(new User(0, "Ahmed Benali", "ahmed@clinic.com",
        //         password, User.UserRole.Doctor));

        // userDao.save(new User(0, "Sara Amrani", "sara@clinic.com",
        //         password, User.UserRole.Doctor));

        // userDao.save(new User(0, "Youssef Alaoui", "youssef@clinic.com",
        //         password, User.UserRole.Doctor));

        // userDao.save(new User(0, "Nadia Mansouri", "nadia@clinic.com",
        //         password, User.UserRole.Nurse));

        // userDao.save(new User(0, "Imane Berrada", "imane@clinic.com",
        //         password, User.UserRole.Nurse));

    }
}
package ma.youcode.clinic.repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import ma.youcode.clinic.DAO.JpaUtil;
import ma.youcode.clinic.Models.User;

public class UserRepository {

public Optional<User> findByEmail(String email) {
    EntityManager em = JpaUtil.createEntityManager();
    try {
        List<User> users = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class)
            .setParameter("email", email)
            .getResultList();
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    } finally {
        em.close();
    }
}
    
}

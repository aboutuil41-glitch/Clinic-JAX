package ma.youcode.clinic.repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import ma.youcode.clinic.DAO.JpaUtil;
import ma.youcode.clinic.Models.Specialist;

public class SpecialistRepository {

    public List<Specialist> findAll() {
        EntityManager em = JpaUtil.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT s FROM Specialist s JOIN FETCH s.user",
                    Specialist.class)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Specialist> findById(int id) {
        EntityManager em = JpaUtil.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Specialist.class, id));
        } finally {
            em.close();
        }
    }
}
package ma.youcode.clinic.repository;

import java.util.Optional;

import jakarta.persistence.EntityManager;
import ma.youcode.clinic.Models.Consultation;
import ma.youcode.clinic.DAO.JpaUtil;

public class Consultationrepo {

    public Optional<Consultation> findById(int id) {
        EntityManager em = JpaUtil.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Consultation.class, id));
        } finally {
            em.close();
        }
    }
}
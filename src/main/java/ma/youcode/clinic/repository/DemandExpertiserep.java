package ma.youcode.clinic.repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import ma.youcode.clinic.DAO.JpaUtil;
import ma.youcode.clinic.Models.DemandeExpertise;


public class DemandExpertiserep {
    public List<DemandeExpertise> getAll(){
        EntityManager em = JpaUtil.createEntityManager();
        List<DemandeExpertise> lits = em.createQuery(
            "SELECT d FROM DemandeExpertise d",
            DemandeExpertise.class
        ).getResultList();
        em.close();
        return lits;
    } 

    public void createDemand(DemandeExpertise d){
        EntityManager em = JpaUtil.createEntityManager();
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        
        em.close();
    }

        public Optional<DemandeExpertise> getById(Long id){
        EntityManager em = JpaUtil.createEntityManager();
        try {
            return Optional.ofNullable(
                em.find(DemandeExpertise.class, id)
            );
        } finally {
            em.close();
        }
    }

      public void update(DemandeExpertise demande) {
        EntityManager em = JpaUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(demande);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}

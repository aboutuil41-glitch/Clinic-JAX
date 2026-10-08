package ma.youcode.clinic.repository;

import java.util.List;

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
}

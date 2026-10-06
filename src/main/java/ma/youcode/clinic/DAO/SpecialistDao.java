package ma.youcode.clinic.DAO;

import java.util.List;

import ma.youcode.clinic.Models.Specialist;

public interface SpecialistDao extends DAO<Specialist>{

    List<Specialist> findAll();
   
}

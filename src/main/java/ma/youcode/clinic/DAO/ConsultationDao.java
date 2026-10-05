package ma.youcode.clinic.DAO;

import java.util.List;

import ma.youcode.clinic.Models.Consultation;

public interface ConsultationDao extends DAO<Consultation> {

    List<Consultation> getAll();
}   
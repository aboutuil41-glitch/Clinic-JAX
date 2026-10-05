package ma.youcode.clinic.DAO;

import java.util.List;
import java.util.Optional;

import ma.youcode.clinic.Models.Patient;

public interface PatientDao extends DAO<Patient> {

    Optional<Patient> findByNumber(String number);

    List<Patient> getAll();
}
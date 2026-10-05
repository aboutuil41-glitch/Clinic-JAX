package ma.youcode.clinic.services;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import ma.youcode.clinic.DAO.PatientDao;
import ma.youcode.clinic.Models.Patient;

public class PatientService {

    private final PatientDao patientDao;

    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public void save(Patient patient) {
        patientDao.save(patient);
    }

    public Optional<Patient> findById(int id) {
        return patientDao.findById(id);
    }

    public Optional<Patient> findByNumber(String number) {
        return patientDao.findByNumber(number);
    }

    public List<Patient> getAll() {
        return patientDao.getAll();
    }

    public List<Patient> getTodayPatients(){
        return patientDao.getAll().stream().filter(p -> p.getArrivalTime().toLocalDate().equals(LocalDate.now())).sorted(Comparator.comparing(Patient::getArrivalTime)).toList();
    }

    public void delete(int id) {
        patientDao.delete(id);
    }
}
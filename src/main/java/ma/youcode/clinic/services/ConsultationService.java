package ma.youcode.clinic.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import ma.youcode.clinic.DAO.ConsultationDao;
import ma.youcode.clinic.Models.Consultation;

public class ConsultationService {

    private final ConsultationDao consultationDao;

    public ConsultationService(ConsultationDao consultationDao) {
        this.consultationDao = consultationDao;
    }

    public void save(Consultation consultation) {
        consultationDao.save(consultation);
    }

    public Optional<Consultation> findById(int id) {
        return consultationDao.findById(id);
    }

    public List<Consultation> getAll() {
        return consultationDao.getAll();
    }

    public List<Consultation> getTodayConsultations() {
        return consultationDao.getAll()
                .stream()
                .filter(c -> c.getTime().toLocalDate().equals(LocalDate.now()))
                .toList();
    }

    public void delete(int id) {
        consultationDao.delete(id);
    }
}
package ma.youcode.clinic.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "reason")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status statue;

    @Column(name = "observation")
    private String observation;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "treatment")
    private String treatment;

    @Column(name = "cost")
    private double cost;

    @Column(name = "date")
    private LocalDateTime time;

    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "doctor_id")
    private int doctorId;

    @OneToMany(mappedBy = "consultation")
    private List<DemandeExpertise> demande = new ArrayList<>();

    public enum Status {
        TERMINEE
    }

    public Consultation() {
        this.time = LocalDateTime.now();
    }

    public Consultation(int id, String reason, Status statue, String observation, String diagnosis,
            String treatment, double cost, LocalDateTime time, int patientId, int doctorId) {
        this.id = id;
        this.reason = reason;
        this.statue = statue;
        this.observation = observation;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.cost = cost;
        this.time = time;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Status getStatue() { return statue; }
    public void setStatue(Status statue) { this.statue = statue; }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public List<DemandeExpertise> getDemande() { return demande; }
}
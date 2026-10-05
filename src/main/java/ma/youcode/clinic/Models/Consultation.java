package ma.youcode.clinic.Models;

import java.time.LocalDateTime;

public class Consultation {
    private int id;

    private String Reason;
    private Status statue;
    private String Observation;
    private String Diagnosis;
    private String Treatment;
    private double Cost;
    private LocalDateTime Time;
    private int PatientId;
    private int DoctorId;

    public enum Status {
        TERMINEE
    }

    public Consultation(int id, String reason, Status statue, String observation, String diagnosis,
            String treatment, double cost, LocalDateTime time, int patientId, int doctorId) {
        this.id = id;
        Reason = reason;
        this.statue = statue;
        Observation = observation;
        Diagnosis = diagnosis;
        Treatment = treatment;
        Cost = cost;
        Time = time;
        PatientId = patientId;
        DoctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public Status getStatue() {
        return statue;
    }

    public void setStatue(Status statue) {
        this.statue = statue;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String observation) {
        Observation = observation;
    }

    public String getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        Diagnosis = diagnosis;
    }

    public String getTreatment() {
        return Treatment;
    }

    public void setTreatment(String treatment) {
        Treatment = treatment;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }

    public LocalDateTime getTime() {
        return Time;
    }

    public void setTime(LocalDateTime time) {
        Time = time;
    }

    public int getPatientId() {
        return PatientId;
    }

    public void setPatientId(int patientId) {
        PatientId = patientId;
    }

    public int getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(int doctorId) {
        DoctorId = doctorId;
    }
}
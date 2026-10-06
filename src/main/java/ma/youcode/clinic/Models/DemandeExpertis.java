package ma.youcode.clinic.Models;

import java.time.LocalDate;

public class DemandeExpertis {
    private int id;
    private int consultationid;
    private String question;
    private Priority priority;
    private Status status;
    private String opinion;
    private String reccomendation;
    private LocalDate creation;

    public enum Priority {
        Urgent,
        Normal,
        Non_Urgent
    }
    public enum Status {
        Pending,
        Completed,
    }

    public DemandeExpertis(int id, int consultationid, String question, Priority priority, Status status,
            String opinion, String reccomendation, LocalDate creation) {
        this.id = id;
        this.consultationid = consultationid;
        this.question = question;
        this.priority = priority;
        this.status = status;
        this.opinion = opinion;
        this.reccomendation = reccomendation;
        this.creation = creation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsultationid() {
        return consultationid;
    }

    public void setConsultationid(int consultationid) {
        this.consultationid = consultationid;
    }

    public String getQuestion() {
        return question;
    }   

    public void setQuestion(String question) {
        this.question = question;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getReccomendation() {
        return reccomendation;
    }

    public void setReccomendation(String reccomendation) {
        this.reccomendation = reccomendation;
    }

    public LocalDate getCreation() {
        return creation;
    }

    public void setCreation(LocalDate creation) {
        this.creation = creation;
    }

}

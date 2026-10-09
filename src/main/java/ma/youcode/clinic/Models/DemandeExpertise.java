package ma.youcode.clinic.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "demande_expertis")
public class DemandeExpertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question")
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "opinion")
    private String avis;

    @Column(name = "reccomendation")   // spelled like this in your table
    private String recommendations;

    @Column(name = "creation")
    private LocalDateTime dateCreation;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private Specialist specialist;

    public DemandeExpertise() {
        this.dateCreation = LocalDateTime.now();
    }

    public enum Priorite {
        URGENTE,
        NORMALE,
        NON_URGENTE
    }

    public enum Status {
        EN_ATTENTE,
        TERMINEE
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public Priorite getPriorite() { return priorite; }
    public void setPriorite(Priorite priorite) { this.priorite = priorite; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getAvis() { return avis; }
    public void setAvis(String avis) { this.avis = avis; }

    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public Consultation getConsultation() { return consultation; }
    public void setConsultation(Consultation consultation) { this.consultation = consultation; }

    public Specialist getSpecialist() { return specialist; }
    public void setSpecialist(Specialist specialist) { this.specialist = specialist; }
}
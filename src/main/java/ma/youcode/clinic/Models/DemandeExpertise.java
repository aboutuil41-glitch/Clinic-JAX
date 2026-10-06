package ma.youcode.clinic.Models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class DemandeExpertise {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int consultationId;
    private int specialisteId;
    private String question;
    @Enumerated(EnumType.STRING)
    private Priorite priorite;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String avis;
    private String recommendations;
    private LocalDate dateCreation;

    public DemandeExpertise() {
        this.dateCreation = LocalDate.now();;
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
}

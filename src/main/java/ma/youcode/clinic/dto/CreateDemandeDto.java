package ma.youcode.clinic.dto;

import ma.youcode.clinic.Models.DemandeExpertise.Priorite;

public class CreateDemandeDto {

    private int consultationId;
    private int specialisteId;
    private String question;
    private Priorite priorite;

    public CreateDemandeDto() {
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    public int getSpecialisteId() {
        return specialisteId;
    }

    public void setSpecialisteId(int specialisteId) {
        this.specialisteId = specialisteId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }
}
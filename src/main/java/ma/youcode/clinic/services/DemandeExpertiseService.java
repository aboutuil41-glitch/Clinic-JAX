package ma.youcode.clinic.services;

import java.util.Optional;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import ma.youcode.clinic.Models.Consultation;
import ma.youcode.clinic.Models.DemandeExpertise;
import ma.youcode.clinic.Models.Specialist;
import ma.youcode.clinic.repository.Consultationrepo;
import ma.youcode.clinic.repository.DemandExpertiserep;
import ma.youcode.clinic.repository.SpecialistRepository;

public class DemandeExpertiseService {
    public void checkUp(int conId,int spe,String question,DemandeExpertise.Priorite priorite){
        Consultationrepo con =  new Consultationrepo();
        SpecialistRepository spo = new SpecialistRepository();

        Optional<Consultation> conn = con.findById(conId);
        Optional<Specialist> spee = spo.findById(spe);

        if (conn.isEmpty()) {
            throw new NotFoundException("Consultation not found");
        }

        if (spee.isEmpty()) {
            throw new NotFoundException("Specialist not found");
        }
        if (question == null || question.isBlank()) {
            throw new BadRequestException("Question cannot be empty");
        }
        if (priorite == null) {
            throw new BadRequestException("Priorite is required");
        }
        Consultation fs = conn.get();
        Specialist gs = spee.get();

        DemandeExpertise demande = new DemandeExpertise();
        demande.setConsultation(fs);
        demande.setSpecialist(gs);
        demande.setQuestion(question);
        demande.setPriorite(priorite);
        demande.setStatus(DemandeExpertise.Status.EN_ATTENTE);


        DemandExpertiserep de = new DemandExpertiserep();
        de.createDemand(demande);
    }

        public void modify(Long id, String answer, String reccomendation){
        DemandExpertiserep de = new DemandExpertiserep();
        if (answer == null || answer.isBlank()) {
            throw new BadRequestException("Question cannot be empty");
        }
        if (reccomendation == null || reccomendation.isBlank()) {
            throw new BadRequestException("Priorite is required");
        }
        

        DemandeExpertise demande = de.getById(id).orElseThrow(() -> new NotFoundException("Demand was not found"));
        demande.setOpinion(answer);
        demande.setRecommendations(reccomendation);
        demande.setStatus(DemandeExpertise.Status.TERMINEE);

        de.update(demande);
    }
}

 
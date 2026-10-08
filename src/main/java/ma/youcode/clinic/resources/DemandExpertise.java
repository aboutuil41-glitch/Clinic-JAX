package ma.youcode.clinic.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.youcode.clinic.dto.CreateDemandeDto;
import ma.youcode.clinic.services.DemandeExpertiseService;

@Path ("/DemandExperttise")
@Produces (MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DemandExpertise {

    private static final DemandeExpertiseService  de = new DemandeExpertiseService();

    @POST 
    public Response createDemand(CreateDemandeDto dto){
        de.checkUp(dto.getConsultationId(), dto.getSpecialisteId(), dto.getQuestion(), dto.getPriorite());
        return Response
        .status(Response.Status.CREATED)
        .entity(dto)
        .build();
    } 
    
}

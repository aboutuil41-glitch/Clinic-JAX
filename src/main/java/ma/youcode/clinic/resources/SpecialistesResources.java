package ma.youcode.clinic.resources;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import ma.youcode.clinic.Models.DemandeExpertise;
import ma.youcode.clinic.Models.Specialist;
import ma.youcode.clinic.Models.User;
import ma.youcode.clinic.dto.DemandeDto;
import ma.youcode.clinic.dto.SpecialisteDto;
import ma.youcode.clinic.filter.TeleSecurityContext;
import ma.youcode.clinic.repository.DemandExpertiserep;
import ma.youcode.clinic.repository.SpecialistRepository;
import ma.youcode.clinic.services.SpecialistesService;

@Path("/specialistes")
@Produces(MediaType.APPLICATION_JSON)
public class SpecialistesResources {

    @Context
    private ContainerRequestContext requestContext;

    private final SpecialistRepository specialistRep = new SpecialistRepository();
    private final SpecialistesService Service = new SpecialistesService();
    private final DemandExpertiserep Demandrep = new DemandExpertiserep();



    @RolesAllowed("GENERALISTE")
    @GET 
    public List<SpecialisteDto> lister(@QueryParam("specialiste") String specialiste){
        return Service.lister(specialiste);
    }

    @GET 
    @Path("/request")
    public Response specialityRequest(){
        User me = ((TeleSecurityContext) requestContext.getSecurityContext()).getUser();


        Specialist specialist = specialistRep.findByUserId(me.getId()).get();
        int Id = specialist.getId();

        Predicate<DemandeExpertise> mine = d -> d.getSpecialist().getId() == Id;
        Comparator<DemandeExpertise> byPriority = Comparator.comparing(DemandeExpertise::getPriorite);

        List<DemandeDto> result = Demandrep.getAll().stream()
                .filter(mine)
                .sorted(byPriority)
                .map(d -> new DemandeDto(
                        d.getId(),
                        d.getQuestion(),
                        d.getPriorite().name(),
                        d.getStatus().name(),
                        d.getAvis(),
                        d.getRecommendations(),
                        String.valueOf(d.getDateCreation()),
                        d.getConsultation().getId(),
                        d.getSpecialist().getId()))
                .toList();

        return Response.ok(result).build();
    }
}

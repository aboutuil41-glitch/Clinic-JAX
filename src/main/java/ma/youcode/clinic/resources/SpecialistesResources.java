package ma.youcode.clinic.resources;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import ma.youcode.clinic.dto.SpecialisteDto;
import ma.youcode.clinic.services.SpecialistesService;

@Path("/specialistes")
@Produces(MediaType.APPLICATION_JSON)
public class SpecialistesResources {
    private final SpecialistesService Service = new SpecialistesService();
    @GET 
    public List<SpecialisteDto> lister(@QueryParam("specialiste") String specialiste){
        return Service.lister(specialiste);
    }
}

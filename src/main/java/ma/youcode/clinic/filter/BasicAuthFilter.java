package ma.youcode.clinic.filter;

import java.util.Base64;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import ma.youcode.clinic.Models.User;
import ma.youcode.clinic.repository.UserRepository;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class BasicAuthFilter implements ContainerRequestFilter {

    private final UserRepository repo = new UserRepository();

    @Override
    public void filter(ContainerRequestContext ctx) {
        try {
            String header = ctx.getHeaderString("Authorization");
            String decoded = new String(Base64.getDecoder().decode(header.substring(6)));

            String email = decoded.substring(0, decoded.indexOf(':'));
            String password = decoded.substring(decoded.indexOf(':') + 1);

            Optional<User> user = repo.findByEmail(email);
            if (user.isEmpty() || !BCrypt.checkpw(password, user.get().getPassword())) {
                ctx.abortWith(Response.status(401).build());
                return;
            }

            ctx.setSecurityContext(new TeleSecurityContext(user.get()));

        } catch (Exception e) {
            ctx.abortWith(Response.status(401).build());
        }
    }
}
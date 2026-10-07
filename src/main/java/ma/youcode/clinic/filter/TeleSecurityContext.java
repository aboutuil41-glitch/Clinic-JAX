package ma.youcode.clinic.filter;

import java.security.Principal;

import jakarta.ws.rs.core.SecurityContext;
import ma.youcode.clinic.Models.User;

public class TeleSecurityContext implements SecurityContext {
     private final User user;

    public TeleSecurityContext(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Principal getUserPrincipal() {
        return () -> user.getEmail();
    }

    @Override
    public boolean isUserInRole(String role) {
        return user.getRole().name().equals(role);
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return "BASIC";
    }
}

package ma.youcode.clinic.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import ma.youcode.clinic.Models.User;

@WebFilter("/user/*")
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String context = req.getContextPath();
        String path = uri.substring(context.length());

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        if (path.startsWith("/user/doctor") &&
            user.getRole() != User.UserRole.GENERALISTE) {

            res.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // if (path.startsWith("/user/nurse") &&
        //     user.getRole() != User.UserRole.Nurse) {

        //     res.sendError(HttpServletResponse.SC_FORBIDDEN);
        //     return;
        // }

        chain.doFilter(request, response);
    }
}

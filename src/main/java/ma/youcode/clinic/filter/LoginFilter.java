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

@WebFilter ("/*")
public class LoginFilter implements Filter {

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


        boolean isLoggedIn = session != null && session.getAttribute("user") != null;
        boolean isPublic = path.equals("/login") || path.equals("/nurse.jsp") || path.startsWith("/api/");
        
        System.out.println("URI: " + uri);
        System.out.println("PATH: " + path);

        if (isLoggedIn || isPublic) {
            System.out.println("ah");
            chain.doFilter(request, response);
            

        } else {
            System.out.println("oh");
            res.sendRedirect(context + "/login");

        }
    }
}
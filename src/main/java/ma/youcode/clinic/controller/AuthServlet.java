package ma.youcode.clinic.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.clinic.DAO.UserJDBC;
import ma.youcode.clinic.Models.User;
import ma.youcode.clinic.services.AuthService;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "AuthServlet", urlPatterns = "/login")
public class AuthServlet extends HttpServlet {
    private AuthService auth = new AuthService(new UserJDBC());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            String context = req.getContextPath();
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            User theAuth = auth.Login(email, password);

            if (theAuth == null) {
                resp.sendRedirect(context + "/login");  
            }
            else{
                req.getSession().setAttribute("user", theAuth);
                resp.sendRedirect(context + "/user/" + theAuth.getRole()); 
            }
            
    }

    @Override 
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
                System.out.println("AUTH SERVLET GET");
                req.getRequestDispatcher("/nurse.jsp").forward(req, resp);
        }
}

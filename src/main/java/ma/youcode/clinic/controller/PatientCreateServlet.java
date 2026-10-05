package ma.youcode.clinic.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import ma.youcode.clinic.DAO.PatientDao;
import ma.youcode.clinic.DAO.PatientJDBC;
import ma.youcode.clinic.Models.Patient;
import ma.youcode.clinic.services.PatientService;

@WebServlet(name = "PatientCreateServlet", urlPatterns = "/nurse/add")

/**
 * PatientCreateServlet
 */
public class PatientCreateServlet extends HttpServlet{
    private PatientService patientService = new PatientService(new PatientJDBC());

    @Override 
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) 
    throws IOException, ServletException
    {
        req.getRequestDispatcher("/Createpatient.jsp").forward(req, resp);
    }
    
    @Override 
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) 
    throws IOException, ServletException{

        String name = req.getParameter("name");
        String firstname = req.getParameter("firstname");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate"));
        String number = req.getParameter("number");
        String bloodPressure = req.getParameter("bloodPressure");
        int heartRate = Integer.parseInt(req.getParameter("heartRate"));
        double temperature = Double.parseDouble(req.getParameter("temperature"));
        int respiratoryRate = Integer.parseInt(req.getParameter("respiratoryRate"));

        Patient patient = new Patient(
                0,
                name,
                firstname,
                birthDate,
                number,
                bloodPressure,
                heartRate,
                temperature,
                respiratoryRate,
                LocalDateTime.now()
        );
        patientService.save(patient);
        resp.sendRedirect(req.getContextPath() + "/user/Nurse");
    }
}
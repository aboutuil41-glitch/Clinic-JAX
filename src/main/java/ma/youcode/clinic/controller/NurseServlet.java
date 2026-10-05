package ma.youcode.clinic.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.youcode.clinic.DAO.PatientDao;
import ma.youcode.clinic.DAO.PatientJDBC;
import ma.youcode.clinic.Models.Patient;
import ma.youcode.clinic.services.PatientService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "NurseServlet", urlPatterns = "/user/Nurse")
public class NurseServlet extends HttpServlet {

    private PatientService patientService = new PatientService(new PatientJDBC());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Patient> patients = patientService.getTodayPatients();

        req.setAttribute("patients", patients);

        req.getRequestDispatcher("/Welcome.jsp").forward(req, resp);
    }

}

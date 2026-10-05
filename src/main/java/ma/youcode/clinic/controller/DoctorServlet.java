package ma.youcode.clinic.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import ma.youcode.clinic.DAO.ConsultationJDBC;
import ma.youcode.clinic.DAO.PatientJDBC;
import ma.youcode.clinic.Models.Consultation;
import ma.youcode.clinic.Models.Patient;
import ma.youcode.clinic.Models.User;
import ma.youcode.clinic.services.ConsultationService;
import ma.youcode.clinic.services.PatientService;

@WebServlet(name = "DoctorServlet", urlPatterns = "/user/Doctor")
public class DoctorServlet extends HttpServlet {

    private PatientService patientService =
            new PatientService(new PatientJDBC());

    private ConsultationService consultationService =
            new ConsultationService(new ConsultationJDBC());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Patient> patients = patientService.getTodayPatients();
        List<Consultation> consultations =
                consultationService.getTodayConsultations();

        patients = patients.stream().filter(patient -> !consultations.stream().map(Consultation::getPatientId).toList().contains(patient.getId()))
        .toList();

        req.setAttribute("patients", patients);

        String patientId = req.getParameter("patientId");

        if (patientId != null) {
            Optional<Patient> selectedPatient =
                    patientService.findById(Integer.parseInt(patientId));

            selectedPatient.ifPresent(patient ->
                    req.setAttribute("selectedPatient", patient)
            );
        }

        req.getRequestDispatcher("/doctors.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        User doctor = (User) session.getAttribute("user");

        int patientId = Integer.parseInt(req.getParameter("patientId"));

        String reason = req.getParameter("reason");
        String observation = req.getParameter("observation");
        String diagnosis = req.getParameter("diagnosis");
        String treatment = req.getParameter("treatment");

        Consultation consultation = new Consultation(
                0,
                reason,
                Consultation.Status.TERMINEE,
                observation,
                diagnosis,
                treatment,
                150,
                java.time.LocalDateTime.now(),
                patientId,
                doctor.getId()
        );

        consultationService.save(consultation);

        resp.sendRedirect(req.getContextPath() + "/user/Doctor");
    }
}
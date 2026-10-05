<%@ page import="java.util.List" %>
<%@ page import="ma.youcode.clinic.Models.Patient" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Doctor</title>
</head>

<body>

<h1>Today's Patients</h1>

<%
    List<Patient> patients =
            (List<Patient>) request.getAttribute("patients");
%>

<table border="1">

    <tr>
        <th>Name</th>
        <th>Firstname</th>
        <th>Social Security Number</th>
        <th>Arrival Time</th>
        <th>Action</th>
    </tr>

<%
    for (Patient patient : patients) {
%>

    <tr>
        <td><%= patient.getName() %></td>
        <td><%= patient.getFirstname() %></td>
        <td><%= patient.getNumber() %></td>
        <td><%= patient.getArrivalTime() %></td>

        <td>
            <a href="<%= request.getContextPath() %>/user/Doctor?patientId=<%= patient.getId() %>">
                Select
            </a>
        </td>
    </tr>

<%
    }
%>

</table>


<%
    Patient selectedPatient =
            (Patient) request.getAttribute("selectedPatient");

    if (selectedPatient != null) {
%>

<hr>

<h2>Patient Information</h2>

<p>
    <strong>Name:</strong>
    <%= selectedPatient.getName() %>
</p>

<p>
    <strong>Firstname:</strong>
    <%= selectedPatient.getFirstname() %>
</p>

<p>
    <strong>Blood Pressure:</strong>
    <%= selectedPatient.getBloodPressure() %>
</p>

<p>
    <strong>Heart Rate:</strong>
    <%= selectedPatient.getHeartRate() %>
</p>

<p>
    <strong>Temperature:</strong>
    <%= selectedPatient.getTemperature() %>
</p>

<p>
    <strong>Respiratory Rate:</strong>
    <%= selectedPatient.getRespiratoryRate() %>
</p>


<h2>Consultation</h2>

<form method="post"
      action="<%= request.getContextPath() %>/user/Doctor">
      <input type="hidden" name="csrfToken" value="${csrfToken}">

    <input type="hidden"
           name="patientId"
           value="<%= selectedPatient.getId() %>">

    <label>Reason:</label>
    <br>
    <textarea name="reason" required></textarea>

    <br><br>

    <label>Observation:</label>
    <br>
    <textarea name="observation" required></textarea>

    <br><br>

    <label>Diagnosis:</label>
    <br>
    <textarea name="diagnosis" required></textarea>

    <br><br>

    <label>Treatment:</label>
    <br>
    <textarea name="treatment" required></textarea>

    <br><br>

    <p><strong>Cost:</strong> 150 DH</p>

    <button type="submit">
        Clôturer
    </button>

</form>

<%
    }
%>

</body>
</html>
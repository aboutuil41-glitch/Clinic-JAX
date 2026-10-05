<%@ page import="java.util.List" %>
<%@ page import="ma.youcode.clinic.Models.Patient" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Waiting List</title>
</head>
<body>

<h1>Waiting List</h1>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Firstname</th>
        <th>Social Security Number</th>
        <th>Arrival Time</th>
        <th>Blood Pressure</th>
        <th>Heart Rate</th>
        <th>Temperature</th>
        <th>Respiratory Rate</th>
    </tr>

<%
    List<Patient> patients = (List<Patient>) request.getAttribute("patients");

    for (Patient patient : patients) {
%>

    <tr>
        <td><%= patient.getName() %></td>
        <td><%= patient.getFirstname() %></td>
        <td><%= patient.getNumber() %></td>
        <td><%= patient.getArrivalTime() %></td>
        <td><%= patient.getBloodPressure() %></td>
        <td><%= patient.getHeartRate() %></td>
        <td><%= patient.getTemperature() %></td>
        <td><%= patient.getRespiratoryRate() %></td>
    </tr>

<%
    }
%>

</table>

<br>

<a href="${pageContext.request.contextPath}/nurse/add">
    Create Patient
</a>

</body>
</html>
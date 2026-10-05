<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Patient</title>
</head>

<body>

<h1>Create Patient</h1>

<form action="${pageContext.request.contextPath}/nurse/add" method="post">
    <input type="hidden" name="csrfToken" value="${csrfToken}">

    <input type="text" name="name" placeholder="Name" required>

    <input type="text" name="firstname" placeholder="Firstname" required>

    <input type="date" name="birthDate" required>

    <input type="text" name="number" placeholder="Phone number" required>

    <input type="text" name="bloodPressure" placeholder="Blood pressure" required>

    <input type="number" name="heartRate" placeholder="Heart rate" required>

    <input type="number" step="0.1" name="temperature" placeholder="Temperature" required>

    <input type="number" name="respiratoryRate" placeholder="Respiratory rate" required>

    <button type="submit">Create Patient</button>

</form>

<br>

<a href="${pageContext.request.contextPath}/nurse">
    Back to Waiting List
</a>

</body>
</html>
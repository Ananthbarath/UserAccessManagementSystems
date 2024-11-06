<%-- Check if the user is an Admin --%>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("Admin")) {
        response.sendRedirect("Login.jsp"); // Redirect if not Admin
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Software</title>
</head>
<body>
    <h2>Create New Software Application</h2>
    <form action="SoftwareServlet" method="post">
        <label for="softwareName">Software Name:</label>
        <input type="text" id="softwareName" name="softwareName" required><br><br>

        <label for="description">Description:</label><br>
        <textarea id="description" name="description" rows="4" cols="50" required></textarea><br><br>

        <label>Access Levels:</label><br>
        <input type="checkbox" name="accessLevel" value="Read"> Read<br>
        <input type="checkbox" name="accessLevel" value="Write"> Write<br>
        <input type="checkbox" name="accessLevel" value="Admin"> Admin<br><br>

        <input type="submit" value="Add Software">
    </form>
</body>
</html>

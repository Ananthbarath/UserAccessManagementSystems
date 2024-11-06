<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Request Access</title>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            fetchSoftwareList();
        });

        function fetchSoftwareList() {
            fetch("SoftwareListServlet")
                .then(response => response.json())
                .then(data => {
                    const softwareDropdown = document.getElementById("softwareId");
                    data.forEach(software => {
                        const option = document.createElement("option");
                        option.value = software.id;
                        option.textContent = software.name;
                        softwareDropdown.appendChild(option);
                    });
                })
                .catch(error => {
                    console.error("Error fetching software list:", error);
                });
        }
    </script>
</head>
<body>
    <h2>Request Access to Software</h2>

    <form action="RequestServlet" method="post">
        <label for="softwareId">Software:</label>
        <select id="softwareId" name="softwareId" required>
            <option value="">Select Software</option>
            <!-- Options will be dynamically populated here -->
        </select><br><br>

        <label for="accessType">Access Type:</label>
        <select id="accessType" name="accessType" required>
            <option value="Read">Read</option>
            <option value="Write">Write</option>
            <option value="Admin">Admin</option>
        </select><br><br>

        <label for="reason">Reason for Request:</label>
        <textarea id="reason" name="reason" required></textarea><br><br>

        <input type="submit" value="Submit Request">
    </form>
</body>
</html>

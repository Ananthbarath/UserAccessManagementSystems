<%@ page import="com.example.Request" %>
<%@ page import="java.util.List" %>
<%
    // Get the pending requests from the request attribute
    List<Request> pendingRequests = (List<Request>) request.getAttribute("pendingRequests");

    // Check if the list is empty
    if (pendingRequests == null || pendingRequests.isEmpty()) {
%>
        <h3>No pending requests found.</h3>
<%
    } else {
%>
        <h2>Pending Access Requests</h2>
        <table border="1">
            <tr>
                <th>Employee Name</th>
                <th>Software Name</th>
                <th>Access Type</th>
                <th>Reason for Request</th>
                <th>Actions</th>
            </tr>
            <% 
                // Loop through the list of pending requests
                for (Request req : pendingRequests) {
            %>
                <tr>
                    <td><%= req.getEmployeeName() %></td>
                    <td><%= req.getSoftwareName() %></td>
                    <td><%= req.getAccessType() %></td>
                    <td><%= req.getReason() %></td>
                    <td>
                        <form action="ApprovalServlet" method="post" style="display: inline;">
                            <input type="hidden" name="requestId" value="<%= req.getId() %>">
                            <input type="hidden" name="action" value="Approve">
                            <button type="submit">Approve</button>
                        </form>
                        <form action="ApprovalServlet" method="post" style="display: inline;">
                            <input type="hidden" name="requestId" value="<%= req.getId() %>">
                            <input type="hidden" name="action" value="Reject">
                            <button type="submit">Reject</button>
                        </form>
                    </td>
                </tr>
            <% 
                } 
            %>
        </table>
<%
    }
%>
</body>
</html>

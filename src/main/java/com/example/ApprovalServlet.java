package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ApprovalServlet")
public class ApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestIdStr = request.getParameter("requestId");
        String action = request.getParameter("action");
        
        if (requestIdStr == null || action == null || (!action.equals("Approve") && !action.equals("Reject"))) {
            response.sendRedirect("pendingRequests.jsp");
            return;
        }

        int requestId;
        try {
            requestId = Integer.parseInt(requestIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("pendingRequests.jsp");
            return;
        }

        String status = action.equals("Approve") ? "Approved" : "Rejected";

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_access_management", "root", "Ananth200!");
            String query = "UPDATE requests SET status = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, status);
            stmt.setInt(2, requestId);
            stmt.executeUpdate();
            
            response.sendRedirect("PendingRequestsServlet"); // Refresh the list of pending requests
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("pendingRequests.jsp");
        }
    }
}

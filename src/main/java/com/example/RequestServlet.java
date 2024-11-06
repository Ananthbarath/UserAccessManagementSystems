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

import com.example.Utility.DBConnection;

@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String softwareIdStr = request.getParameter("softwareId");
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        // Validate that softwareId is not null or empty
        if (softwareIdStr == null || softwareIdStr.isEmpty()) {
            // Handle the error (e.g., redirect to an error page, or add an error message)
            request.setAttribute("errorMessage", "Please select a software application.");
            request.getRequestDispatcher("requestAccess.jsp").forward(request, response);
            return;
        }

        int softwareId;
        int userId=1;
        try {
            softwareId = Integer.parseInt(softwareIdStr); // Parse the software ID
        } catch (NumberFormatException e) {
            // Handle the error (e.g., log it, and show an error message)
            request.setAttribute("errorMessage", "Invalid software ID.");
            request.getRequestDispatcher("requestAccess.jsp").forward(request, response);
            return;
        }

        // Proceed with database operation if all inputs are valid
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_access_management", "root", "Ananth200!");
        		String query = "INSERT INTO requests (user_id, software_id, access_type, reason) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            
			stmt.setInt(1, userId);
            stmt.setInt(2, softwareId);
            stmt.setString(3, accessType);
            stmt.setString(4, reason);
            stmt.executeUpdate();
            response.sendRedirect("success.jsp"); // Redirect to a success page
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to submit the request. Please try again.");
            request.getRequestDispatcher("requestAccess.jsp").forward(request, response);
        }
    }
}

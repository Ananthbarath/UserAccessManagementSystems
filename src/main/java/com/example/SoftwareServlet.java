package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SoftwareServlet")
public class SoftwareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        // Check if the user is an Admin
        if (role == null || !role.equals("Admin")) {
            response.sendRedirect("Login.jsp"); // Redirect if not Admin
            return;
        }

        // Get form parameters
        String softwareName = request.getParameter("softwareName");
        String description = request.getParameter("description");
        String[] accessLevels = request.getParameterValues("accessLevel");
        
        // Convert access levels to a comma-separated string
        String accessLevelsStr = String.join(", ", accessLevels);

        // Insert the new software into the database
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_access_management", "root", "Ananth200!");
            String query = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, softwareName);
            stmt.setString(2, description);
            stmt.setString(3, accessLevelsStr);
            stmt.executeUpdate();

            // Redirect to a confirmation page or back to the createSoftware page
            response.sendRedirect("createSoftware.jsp?success=true");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
            response.sendRedirect("createSoftware.jsp?error=true");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("General Exception: " + e.getMessage());
            response.sendRedirect("createSoftware.jsp?error=true");
        }
    }
}


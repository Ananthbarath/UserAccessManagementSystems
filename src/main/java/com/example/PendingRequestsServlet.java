package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.Utility.DBConnection;

@WebServlet("/PendingRequestsServlet")
public class PendingRequestsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Request> pendingRequests = new ArrayList<>();
        
        // Fetch pending requests from the database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_access_management", "root", "Ananth200!");

            String query = "SELECT r.id, u.username AS employeeName, s.name AS softwareName, r.access_type, r.reason " +
                    "FROM requests r " +
                    "JOIN users u ON r.user_id = u.id " +
                    "JOIN software s ON r.software_id = s.id " +
                    "WHERE r.status = 'Pending'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create a Request object for each row in the result set
                Request requestObj = new Request();
                requestObj.setId(rs.getInt("id"));
                requestObj.setEmployeeName(rs.getString("employeeName"));
                requestObj.setSoftwareName(rs.getString("softwareName"));
                requestObj.setAccessType(rs.getString("access_type"));
                requestObj.setReason(rs.getString("reason"));

                // Add to the pending requests list
                pendingRequests.add(requestObj);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the list as a request attribute
        request.setAttribute("pendingRequests", pendingRequests);

        // Forward the request to the JSP page
        request.getRequestDispatcher("pendingRequests.jsp").forward(request, response);
    }
}

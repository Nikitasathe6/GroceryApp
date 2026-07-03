package com.grocery.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

import com.grocery.db.DBConnection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // ✅ Handle GET (fix for 405 error)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("⚠️ GET request received, redirecting to POST...");
        doPost(request, response); // redirect GET → POST
    }

    // ✅ Main login logic
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {

            System.out.println("🔥 LoginServlet Called");

            Connection conn = DBConnection.getConnection();

            if (conn != null) {
                System.out.println("✅ Database Connected");
            } else {
                System.out.println("❌ Database NOT Connected");
            }

            // ✅ Query
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String role = rs.getString("role");

                HttpSession session = request.getSession();
                session.setAttribute("username", email);
                session.setAttribute("role", role);

                System.out.println("✅ Login Success: " + role);

                // ✅ Role-based redirect
                if ("Admin".equalsIgnoreCase(role)) {

                    response.sendRedirect(request.getContextPath() + "/admin-dashboard.jsp");

                } else {

                    response.sendRedirect(request.getContextPath() + "/index.html");
                }

            } else {

                System.out.println("❌ Invalid Login");

                response.sendRedirect(request.getContextPath() + "/login.html?error=Invalid+Credentials");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}

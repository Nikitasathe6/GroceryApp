package com.grocery.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.grocery.db.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // ✅ Get ID safely
            String idStr = request.getParameter("id");

            if (idStr == null || idStr.isEmpty()) {
                response.getWriter().println("❌ ID not received");
                return;
            }

            int id = Integer.parseInt(idStr);

            // ✅ DB connection
            Connection conn = DBConnection.getConnection();

            if (conn == null) {
                response.getWriter().println("❌ Database not connected");
                return;
            }

            // ✅ Delete query
            PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM products WHERE product_id=?"
            );

            ps.setInt(1,id);

            int result = ps.executeUpdate();

            // ✅ Check result
            if (result > 0) {
                System.out.println("✅ Product Deleted Successfully");
            } else {
                System.out.println("❌ Product Not Found");
            }

            // ✅ Redirect back
            response.sendRedirect("admin-dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
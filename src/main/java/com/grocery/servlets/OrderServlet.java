package com.grocery.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.grocery.db.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productName = request.getParameter("product");
        int qty = Integer.parseInt(request.getParameter("qty"));
        int price = Integer.parseInt(request.getParameter("price"));

        int total = qty * price;

        try {

            Connection conn = DBConnection.getConnection();

            // ================= INSERT ORDER =================
            String sql = "INSERT INTO orders(username, product_name, quantity, total_price) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "user");
            ps.setString(2, productName);
            ps.setInt(3, qty);
            ps.setInt(4, total);
            ps.executeUpdate();


            // ================= UPDATE PRODUCT STOCK =================
            PreparedStatement ps2 = conn.prepareStatement(
                    "UPDATE products SET stock = stock - 1 WHERE name=?"
            );

            ps2.setString(1, productName);
            ps2.executeUpdate();


            response.getWriter().println("Order Saved Successfully");

        } catch (Exception e) {

            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());

        }
    }
}
package com.grocery.servlets;

import java.io.IOException;
import java.sql.Connection;

import com.grocery.db.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TestDB")
public class TestDB extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                response.getWriter().println("✅ Database Connected Successfully!");
            } else {
                response.getWriter().println("❌ Failed to connect to Database!");
            }
        } catch (Exception e) {
            response.getWriter().println("❌ Connection Error: " + e.getMessage());
        }
    }
}
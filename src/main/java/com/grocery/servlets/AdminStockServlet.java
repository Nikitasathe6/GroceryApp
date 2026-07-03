package com.grocery.servlets;

import java.io.IOException;
import java.io.PrintWriter;    
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;     

import com.grocery.db.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminStock")
public class AdminStockServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;  

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement("SELECT name, stock FROM products");

            ResultSet rs = ps.executeQuery();

            res.setContentType("text/html");

            PrintWriter out = res.getWriter();

            out.println("<h2>Product Stock</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Product</th><th>Stock</th></tr>");

            while (rs.next()) {

                out.println("<tr>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getInt("stock") + "</td>");
                out.println("</tr>");

            }

            out.println("</table>");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
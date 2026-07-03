package com.grocery.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/BillingServlet")
public class BillingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Get Form Data
        String customer = request.getParameter("customer");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String payment = request.getParameter("payment");

        String items = request.getParameter("items");
        String qty = request.getParameter("qty");

        String totalValue = request.getParameter("total");

        // Safety check
        if (items == null) items = "";
        if (qty == null || qty.equals("")) qty = "0";
        if (totalValue == null || totalValue.equals("")) totalValue = "0";

        // Calculate Bill
        double subtotal = Double.parseDouble(totalValue);
        double gst = subtotal * 0.18;
        double finalTotal = subtotal + gst;

        // Send to JSP
        request.setAttribute("customer", customer);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.setAttribute("payment", payment);

        request.setAttribute("items", items);
        request.setAttribute("qty", qty);

        request.setAttribute("subtotal", subtotal);
        request.setAttribute("gst", gst);
        request.setAttribute("total", finalTotal);

        request.getRequestDispatcher("bill-print.jsp")
               .forward(request, response);
    }
}













<%@ page import="java.sql.*" %>
<%@ page import="com.grocery.db.DBConnection" %>

<%
int id = Integer.parseInt(request.getParameter("id"));

Connection conn = DBConnection.getConnection();
PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id=?");
ps.setInt(1, id);
ResultSet rs = ps.executeQuery();

rs.next();
%>

<form action="UpdateProductServlet" method="post">
    <input type="hidden" name="id" value="<%= id %>">

    Name: <input type="text" name="name" value="<%= rs.getString("name") %>"><br>
    Price: <input type="number" name="price" value="<%= rs.getDouble("price") %>"><br>
    Stock: <input type="number" name="stock" value="<%= rs.getInt("stock") %>"><br>

    <button>Update</button>
</form>
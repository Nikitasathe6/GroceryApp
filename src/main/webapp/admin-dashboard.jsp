<%@ page import="java.sql.*" %>
<%@ page import="com.grocery.db.DBConnection" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
Connection conn = DBConnection.getConnection();

// get products
PreparedStatement ps = conn.prepareStatement("SELECT * FROM products");
ResultSet rs = ps.executeQuery();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Panel</title>

<style>
body { font-family: Arial; margin:0; }
.header { background: green; color:white; padding:15px; }
.container { padding:20px; }
table { width:100%; border-collapse: collapse; }
th, td { padding:10px; border:1px solid #ddd; text-align:center; }
th { background: green; color:white; }
</style>

</head>

<body>

<div class="header">Admin Dashboard</div>

<div class="container">

<!-- ADD PRODUCT -->
<h3>Add Product</h3>
<form action="AddProductServlet" method="post">
    Name: <input type="text" name="name" required>
    Price: <input type="number" name="price" required>
    Stock: <input type="number" name="stock" required>
    <button>Add</button>
</form>

<hr>

<!-- PRODUCT TABLE -->
<h3>Products</h3>

<table>
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
    <th>Stock</th>
    <th>Action</th>
</tr>

<%
while(rs.next()){
%>

<tr>
    <td><%= rs.getInt("product_id") %></td>
    <td><%= rs.getString("name") %></td>
    <td>₹<%= rs.getDouble("price") %></td>
    <td><%= rs.getInt("stock") %></td>
    <td>
    	<a href="edit-product.jsp?id=<%= rs.getInt("product_id") %>">Edit</a>
        <a href="DeleteProductServlet?id=<%= rs.getInt("product_id") %>">Delete</a>
    </td>
</tr>

<% } %>

</table>

</div>

</body>
</html>
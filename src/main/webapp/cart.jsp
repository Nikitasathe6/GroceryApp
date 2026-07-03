<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.Connection,java.sql.PreparedStatement,java.sql.ResultSet" %>
<%@ page import="com.grocery.db.DBConnection" %>

<%
Connection conn = DBConnection.getConnection();

PreparedStatement ps = conn.prepareStatement(
"SELECT p.name, p.price FROM cart c JOIN products p ON c.product_id = p.product_id"
);

ResultSet rs = ps.executeQuery();

double total = 0;
%>

<!DOCTYPE html>
<html>
<head>
<title>My Cart</title>

<link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">

<style>

body{
font-family:'Poppins',sans-serif;
background:#f3f4f6;
margin:0;
padding:20px;
}

h2{
text-align:center;
color:#111827;
}

table{
width:60%;
margin:auto;
border-collapse:collapse;
background:white;
border-radius:10px;
overflow:hidden;
box-shadow:0px 5px 15px rgba(0,0,0,0.2);
}

th{
background:#22c55e;
color:white;
padding:12px;
}

td{
padding:12px;
text-align:center;
border-bottom:1px solid #ddd;
}

tr:hover{
background:#f9fafb;
}

.total-row{
font-weight:bold;
background:#e5e7eb;
}

.btn{
display:block;
margin:20px auto;
padding:10px 20px;
background:#22c55e;
color:white;
text-decoration:none;
border-radius:5px;
text-align:center;
width:220px;
}

.btn:hover{
background:#16a34a;
}

</style>
</head>

<body>

<h2>🛒 Your Cart</h2>

<table>

<tr>
<th>Product</th>
<th>Price</th>
</tr>

<%
while(rs.next()){
total += rs.getDouble("price");
%>

<tr>
<td><%= rs.getString("name") %></td>
<td>₹<%= rs.getDouble("price") %></td>
</tr>

<%
}
%>

<tr class="total-row">
<td>Total</td>
<td>₹<%= total %></td>
</tr>

</table>

<a href="index.jsp" class="btn">⬅ Continue Shopping</a>

</body>
</html>
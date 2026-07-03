<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FreshMart Invoice</title>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">

<style>
*{margin:0;padding:0;box-sizing:border-box;}

body{
font-family:'Poppins',sans-serif;
background:linear-gradient(135deg,#f0fdf4,#dcfce7);
padding:40px;
}

.bill-container{
max-width:850px;
margin:auto;
background:#fff;
border-radius:18px;
box-shadow:0 15px 40px rgba(0,0,0,0.12);
overflow:hidden;
}

/* HEADER */
.bill-header{
background:linear-gradient(135deg,#16a34a,#22c55e);
color:white;
padding:30px;
display:flex;
justify-content:space-between;
align-items:center;
}

.brand h1{font-size:32px;font-weight:700;}
.brand p{font-size:14px;opacity:0.9;margin-top:5px;}
.invoice-box{text-align:right;}
.invoice-box h2{font-size:26px;}

/* CONTENT */
.content{padding:30px;}

/* INFO */
.info-grid{
display:grid;
grid-template-columns:1fr 1fr;
gap:20px;
margin-bottom:20px;
}

.card{
background:#f9fafb;
padding:18px;
border-radius:12px;
border:1px solid #e5e7eb;
}

/* TABLE */
table{
width:100%;
border-collapse:collapse;
margin-top:10px;
border-radius:12px;
overflow:hidden;
}

th{
background:#22c55e;
color:white;
padding:12px;
}

td{
padding:12px;
text-align:center;
border-bottom:1px solid #eee;
}

/* SUMMARY */
.summary-box{
margin-top:25px;
margin-left:auto;
width:320px;
background:#f9fafb;
padding:20px;
border-radius:14px;
border:1px solid #e5e7eb;
}

.summary-row{
display:flex;
justify-content:space-between;
margin:10px 0;
}

.total{
font-size:18px;
font-weight:700;
color:#16a34a;
border-top:2px dashed #ccc;
padding-top:10px;
margin-top:10px;
}

/* BUTTONS */
.btn-area{text-align:center;padding:30px;}
button{
padding:12px 20px;
border:none;
border-radius:10px;
margin:5px;
cursor:pointer;
font-weight:600;
}
.print-btn{background:#16a34a;color:white;}
.home-btn{background:#111827;color:white;}

@media print{
button{display:none;}
body{background:white;}
.bill-container{box-shadow:none;}
}
</style>
</head>

<body>

<div class="bill-container">

<!-- HEADER -->
<div class="bill-header">
<div class="brand">
<h1>FreshMart</h1>
<p>Smart Grocery Billing System</p>
</div>

<div class="invoice-box">
<h2>INVOICE</h2>
<p>Date: <%= new java.util.Date() %></p>
</div>
</div>

<div class="content">

<!-- CUSTOMER -->
<div class="info-grid">

<div class="card">
<h3>Customer Details</h3>
<p><b>Name:</b> <%= request.getAttribute("customer") %></p>
<p><b>Phone:</b> <%= request.getAttribute("phone") %></p>
<p><b>Address:</b> <%= request.getAttribute("address") %></p>
</div>

<div class="card">
<h3>Payment</h3>
<p><b>Method:</b> <%= request.getAttribute("payment") %></p>
<p><b>Status:</b> Paid</p>
<p><b>Order ID:</b> FM<%= System.currentTimeMillis() %></p>
</div>

</div>

<!-- ITEMS TABLE -->

<!-- SUMMARY -->
<div class="summary-box">

<div class="summary-row">
<span>Total</span>
<span>₹<%= request.getAttribute("subtotal") %></span>
</div>

<div class="summary-row">
<span>GST (18%)</span>
<span>₹<%= request.getAttribute("gst") %></span>
</div>

<div class="summary-row total">
<span>Grand Total</span>
<span>₹<%= request.getAttribute("total") %></span>
</div>

</div>

</div>

</div>

<!-- BUTTONS -->
<div class="btn-area">
<button class="print-btn" onclick="window.print()">Print Bill</button>
<button class="home-btn" onclick="window.location='index.html'">Back Home</button>
</div>

</body>
</html>
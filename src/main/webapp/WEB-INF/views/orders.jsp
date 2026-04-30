<%@ page import="java.util.*, com.fashionmashup.model.Order" %>
<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Orders</title>

    <!-- CSS -->
    <link rel="stylesheet" href="/fashionmashup/assets/css/navbar.css">
    <link rel="stylesheet" href="/fashionmashup/assets/css/orders.css">
</head>

<body>

<!-- NAVBAR -->
<div class="navbar">
    <h2>Fashion Mashup</h2>
    <div>
        <a href="products">Products</a>
        <a href="cart">Cart</a>
        <a href="logout">Logout</a>
    </div>
</div>

<h2 style="text-align:center;">My Orders</h2>

<div class="orders-container">

<%
if (orders == null || orders.isEmpty()) {
%>
    <p style="text-align:center;">No orders yet.</p>
<%
} else {
    for (Order o : orders) {
%>

    <div class="order-card">
        <h3>Order #<%= o.getOrderId() %></h3>
        <p><b>Status:</b> <%= o.getOrderStatus() %></p>
        <p><b>Date:</b> <%= o.getOrderDate() %></p>
    </div>

<%
    }
}
%>

</div>

</body>
</html>
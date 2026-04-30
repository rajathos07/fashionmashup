<%@ page import="java.util.*, com.fashionmashup.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Products</title>

    <link rel="stylesheet" href="/fashionmashup/assets/css/products.css">
    <link rel="stylesheet" href="/fashionmashup/assets/css/navbar.css">
</head>

<body>

<div class="navbar">
    <h2>Fashion Mashup</h2>
    <div>
        <a href="products">Products</a>
        <a href="cart">Cart</a>
        <a href="orders">Orders</a>
        <a href="logout">Logout</a>
    </div>
</div>

<h2 style="text-align:center;">Our Products</h2>

<div class="product-container">

<%
if(products != null){
    for(Product p : products){
%>

    <div class="product-card">

<img src="${pageContext.request.contextPath}/assets/images/t-shirt.webp" alt="product">
        <div class="info">
            <h3><%= p.getProductName() %></h3>
            <p>₹ <%= p.getPrice() %></p>
            <p class="discount"><%= p.getDiscountPercent() %>% OFF</p>
        </div>

        <a class="view-btn" href="product-details?id=<%= p.getProductId() %>">View</a>

    </div>

<%
    }
}
%>

</div>

</body>
</html>
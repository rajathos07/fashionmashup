<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.fashionmashup.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fashionmashup.model.ProductSize" %>

<%
    Product p = (Product) request.getAttribute("product");
    List<ProductSize> sizes = (List<ProductSize>) request.getAttribute("sizes");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Product Details</title>

    <!-- ✅ FIXED CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/product-details.css?v=2">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/navbar.css?v=2">

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

<div class="container">

    <div class="image-section">
        <!-- ✅ FIXED IMAGE -->
        <img src="<%= request.getContextPath() %>/assets/images/<%= p.getImageUrl() %>" alt="product">
    </div>

    <div class="details-section">
        <h2><%= p.getProductName() %></h2>
        <p><%= p.getDescription() %></p>
        <h3><%= p.getDiscountPercent() %> % OFF</h3>

        <h4>Select Size:</h4>

        <form action="add-to-cart" method="post">
            <input type="hidden" name="productId" value="<%= p.getProductId() %>">

            <div class="sizes">
                <% for(ProductSize s : sizes){ %>
                    <label>
                        <input type="radio" name="size" value="<%= s.getSizeLabel() %>" required>
                        <%= s.getSizeLabel() %>
                    </label>
                <% } %>
            </div>

            <input type="number" name="quantity" value="1" min="1">

            <button type="submit">Add to Cart</button>
        </form>
    </div>

</div>

</body>
</html>
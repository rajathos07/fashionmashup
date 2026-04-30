<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fashionmashup.model.CartItem" %>

<%
    List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>

    <!-- CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/cart.css?v=3">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/navbar.css?v=3">
</head>
<body>

<div class="navbar">
    <h2>Fashion Mashup</h2>
    <div>
        <!-- ✅ FIX: use contextPath -->
        <a href="<%= request.getContextPath() %>/products">Products</a>
        <a href="<%= request.getContextPath() %>/orders">Orders</a>
        <a href="<%= request.getContextPath() %>/logout">Logout</a>
    </div>
</div>

<h2 style="text-align:center;">Your Cart</h2>

<div class="cart-container">

<%
if(cartItems != null && !cartItems.isEmpty()){
    for(CartItem item : cartItems){
%>

    <div class="cart-card">
        <h3><%= item.getProductName() %></h3>
        <p>Size: <%= item.getSizeLabel() %></p>
        <p>Quantity: <%= item.getQuantity() %></p>

        <!-- ✅ FIX: correct method -->
        <p>Price: ₹ <%= item.getUnitPrice() %></p>

        <!-- REMOVE BUTTON -->
        <form action="<%= request.getContextPath() %>/remove-cart" method="post">
            <input type="hidden" name="cartItemId" value="<%= item.getCartItemId() %>">
            <button class="remove-btn">Remove</button>
        </form>
    </div>

<%
    }
%>

    <!-- ✅ CHECKOUT BUTTON (FINAL FIX) -->
    <div style="text-align:center;">
        <a href="<%= request.getContextPath() %>/checkout" class="checkout-btn">
    Proceed to Checkout
</a>
    </div>

<%
} else {
%>
    <h3 style="text-align:center;">Cart is empty</h3>

    <!-- ✅ BONUS FIX: redirect option -->
    <div style="text-align:center; margin-top:20px;">
        <a href="<%= request.getContextPath() %>/products" class="checkout-btn">
            Go to Products
        </a>
    </div>

<%
}
%>

</div>

</body>
</html>
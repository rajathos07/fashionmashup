<%@ page import="java.util.*, com.fashionmashup.model.CartItem" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
    double total = 0;
%>

<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/navbar.css?v=3">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/checkout.css?v=2"></head>

<body>

<!-- NAVBAR -->
<div class="navbar">
    <h2>Fashion Mashup</h2>
    <div>
        <a href="products">Products</a>
        <a href="cart">Cart</a>
        <a href="orders">Orders</a>
        <a href="logout">Logout</a>
    </div>
</div>

<div class="checkout-container">

    <!-- LEFT: ITEMS -->
    <div class="checkout-items">

        <h2>Your Order</h2>

        <%
        if(cartItems != null){
            for(CartItem item : cartItems){
                total += item.getUnitPrice() * item.getQuantity();
        %>

        <div class="item-card">
            <div>
                <h3><%= item.getProductName() %></h3>
                <p>Size: <%= item.getSizeLabel() %></p>
                <p>Qty: <%= item.getQuantity() %></p>
            </div>
            <div class="price">
                ₹ <%= item.getUnitPrice() %>
            </div>
        </div>

        <%
            }
        }
        %>

    </div>

    <!-- RIGHT: SUMMARY -->
    <div class="checkout-summary">

        <h2>Summary</h2>

        <p>Total Amount</p>
        <h3>₹ <%= total %></h3>

        <form action="place-order" method="post">

    <!-- IMPORTANT: SEND TOTAL -->
    <input type="hidden" name="total" value="<%= total %>">

    <label>Payment Method</label>
    <select name="paymentMethod" required>
        <option value="COD">Cash on Delivery</option>
        <option value="CARD">Card</option>
        <option value="UPI">UPI</option>
    </select>

    <button type="submit">Place Order</button>

</form>

    </div>

</div>

</body>
</html>
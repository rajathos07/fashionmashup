<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Success</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/order-success.css">

   
    <meta http-equiv="refresh" content="3;URL=${pageContext.request.contextPath}/orders">
</head>
<body>

<div class="success-container">

    <div class="checkmark-circle">
        <div class="checkmark"></div>
    </div>

    <h1>Order Placed Successfully 🎉</h1>
    <p>Thank you for shopping with Fashion Mashup</p>

    <a href="products" class="btn">Continue Shopping</a>

</div>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Fashion Mashup</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">
</head>
<body>

<div class="container">
    <div class="login-box">

        <h2>Fashion Mashup</h2>
        <p>Login to your account</p>

        <form action="login" method="post">
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>

            <button type="submit">Login</button>
        </form>

        <p class="register-link">
            Don't have an account? <a href="register">Register</a>
        </p>

    </div>
</div>

</body>
</html>
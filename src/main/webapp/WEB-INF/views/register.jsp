<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Fashion Mashup</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">
</head>
<body>

<div class="container">
    <div class="login-box">

        <h2>Create Account</h2>

        <form action="register" method="post">
            <input type="text" name="fullName" placeholder="Full Name" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="text" name="phone" placeholder="Phone" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="text" name="gender" placeholder="Gender" required>
            <input type="text" name="address" placeholder="Address" required>

            <button type="submit">Register</button>
        </form>

        <p class="register-link">
            Already have an account? <a href="login">Login</a>
        </p>

    </div>
</div>

</body>
</html>
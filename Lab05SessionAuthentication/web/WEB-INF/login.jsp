<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="POST" action="" id="loginPage">
            <label>Username:</label>
            <input type="text" name="user_name" value="${userName}">
            <br>
            <label>Password:</label>
            <input type="text" name="password_" value="${passWord}">
            <br><br>
            <input type="submit" value="Login" id="login">
            <input type="hidden" name="action" value="login">
        </form>
    </body>
</html>

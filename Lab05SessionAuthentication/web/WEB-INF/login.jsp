<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="POST" action="login" id="loginPage">
            <label>Username:</label>
            <input type="text" name="username" value="${username}">
            <br>
            <label>Password:</label>
            <input type="text" name="password" value="${password}">
            <br><br>
            <input type="submit" value="Login" id="login">
            <c:if test="${logoutMessage}">
                <p>Logged out</p>
            </c:if>
            <c:if test="${invalid}">
                <p>Invalid credentials</p>
            </c:if>
        </form>
    </body>
</html>

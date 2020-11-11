<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Login Page Tracing app</h1>
            <p style="color:red">wrong username/password try again.</p>
        <form method="post" action="j_security_check">
            <label for="username">Username:</label>
            <input type="text" name="j_username"><br><br>
            <label for="password">Password: </label>
            <input type="password" name="j_password"><br><br>
            <button type="submit" name="submit" value="fromLogin">login</button>
        </form>
    </body>
</html>
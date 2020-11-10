<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welkom iedereen</h1>
        <form method= "post" action="users/welkomUser.jsp" >
        <button type="submit" name="submit" value="naarWelkom">Gebruiker</button>
        </form>
        <form method= "post" action="users/welkomUser.jsp" >
            <button type="submit" name="submit" value="docter">Dokter</button>            
        </form>
    </body>
</html>

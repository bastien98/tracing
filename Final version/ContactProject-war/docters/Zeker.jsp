<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <h1>Bevestig de test voor gebruiker: ${testName} </h1>
        <form method="post" action="<c:url value='Controller'/>">
            <button type="submit" name="submit" value="naarZeker">Bevestig</button><br>            
        </form>
        <form method="post" action="<c:url value='Controller'/>">
            <button type="submit" name="submit" value="naarWelkomDrs">ga terug</button>            
        </form>
        <jsp:include page="/footer.jsp"/><br>
    </body>
</html>

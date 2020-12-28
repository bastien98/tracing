<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>afmelden</title>
    </head>
    <body>
        <footer>
            <br><br>          
             <form method="post" action="<c:url value='/Controller'/>"> 
                <button type="submit" name="submit" value ="fromFooter">afmelden</button>
            </form>
        </footer>
    </body>
</html>

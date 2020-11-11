<%-- 
    Document   : welkom
    Created on : 6-nov-2020, 18:33:27
    Author     : basti
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome ${request.getUserPrincipal()} > 
  </title>
    </head>
    <body>
        <form method="post" action="<c:url value='/controller'/>">
            <h1> Welkom  gebruikers</h1>
             <c:if test="${status == 'updated'}">
                <p style="color:green">Contact is upgedate.</p>
            </c:if>
            <c:if test="${status == 'new'}">
                <p style="color:green">Nieuw contact is geregistreerd.</p>
            </c:if>
                <button type="submit" name="submit" value="registreer">registreer een contact die je hebt gehad.</button><br><br>
                <button type="submit" name="submit" value="statusRaadplegen">Status raadplegen</button>
        </form><br>
         <jsp:include page="/footer.jsp"/>
                
            
                    
               
    </body>
</html>

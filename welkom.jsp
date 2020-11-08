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
        <title>Welcome</title>
    </head>
    <body>
        <form method="post" action="<c:url value='controller'/>">
             <c:if test="${status == 'updated'}">
                <p style="color:greenyellow">Contact is upgedate.</p>
            </c:if>
            <c:if test="${status == 'new'}">
                <p style="color:greenyellow">Nieuw contact is geregistreerd.</p>
            </c:if>
            <button type="submit" name="submit" value="registreer">registreer een contact die je hebt gehad.</button>
        
        </form>
        
                
            
                    
               
    </body>
</html>

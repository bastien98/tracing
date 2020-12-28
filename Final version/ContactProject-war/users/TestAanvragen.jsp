<%-- 
    Document   : testAanvragen
    Created on : 11-nov-2020, 0:43:15
    Author     : basti
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            h1{
               
                background-color: black;
                color: white;
                height: 50px;
                text-align: center;
                line-height: 50px;
                font-style: italic;
            }
        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Test aanvragen</h1>
        <br>
        <form method="post" action="Controller">
            <c:out value="${sessionScope.currentUsername}, je hebt een test aangevraagd. Wacht op het resultaat"/><br>
            <c:out value="${sessionScope.testId}, dit is het nummer van jouw test en de naam van de arts is ${sessionScope.doctorName}" /><br><br>
            <button type="submit" name="submit" value="naarWelkomFromCoronaTest">Ga terug</button>
        </form><br>
        <br><br>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>

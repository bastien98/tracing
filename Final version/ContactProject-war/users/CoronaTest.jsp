<%-- 
    Document   : coronaTest
    Created on : Nov 21, 2020, 9:51:11 PM
    Author     : karim
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
        <h1>Corona Test</h1>
        
            <c:choose>
               
                <c:when test="${sessionScope.testDone == 'yes'}">
                  <form method="post" action="Controller">
                        <p style="color:green">Je hebt al corona test aangevraagd. Wacht a.u.b op het resultaat</p>
                        <c:set var="test" scope="session" value="no"/>
                        <button type="submit" name="submit" value="naarWelkom">Ga terug</button>
                  
                  </form>
                </c:when>
                
            <c:otherwise>
            <form method="post" action="Controller">
            <select name="selectedDoctor">
                <c:out value="Kies jouw arts"/>
                <c:forEach var="doctor" items="${doctors}">
                    
                    <option value="${doctor.getUsername()}"> ${doctor.getUsername()}</option>
                </c:forEach>
            </select>
            <br><br>
            <button type="submit" name="submit" value="testAanvragen">Test aanvragen</button><br><br>
            <button type="submit" name="submit" value="naarWelkom">Ga terug</button>
            </form>
                </c:otherwise> 
            </c:choose>
        
    </body>
</html>

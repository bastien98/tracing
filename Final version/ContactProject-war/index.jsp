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
    <body >
        <h1 >Welkom iedereen</h1>
        <form method= "post" action="users/WelkomUser.jsp" >
        <c:set var="first" scope="session" value="yes"/>
        <button type="submit" name="submit" value="naarWelkom" >Gebruiker</button>
        
        </form>
        <form method= "post" action="docters/WelkomDokters.jsp" >
            <button type="submit" name="submit" value="naarWelkomDrs">Dokter</button>            
        </form>
        <c:set var="test" scope="session" value="yes"/>
    </body>
</html>

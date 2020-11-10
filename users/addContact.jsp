<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>addContact</title>
    </head>
    <body>
        <form method="post" action="<c:url value='controller'/>">
            <p>selecteer een gebruiker waarmee je een contact hebt gehad: </p>
            <c:if test="${status == 'double'}">
                <p style="color:red">Contact is al gerigistreerd geweest.</p>
            </c:if>
            <select name="selectedUsername">
                <c:forEach var="user" items="${users}">
                    <c:set var = "sameUser" scope = "session" value = "0"/>
                    <c:if test ="${currentUsername == user.getUsername()}">
                        <c:set var = "sameUser" scope = "session" value = "1"/>                        
                    </c:if>
                    <c:if test ="${sameUser == 0}">
                         <option value=${user.getUsername()}> ${user.getUsername()} </option>   
                    </c:if>
                </c:forEach>
            </select>
            
            <p>selecteer het soort van contact die je hebt gehad: </p>
            <select name="selectedSort">
                <option value="nauw">nauw contact (afstand werd niet bewaard)</option>
                <option value="middelmatig"> gewoon contact (afstand van 1,5m werd gerespecteerd)</option>
                <option value="veilig"> veilig contact (minder dan 15 minuten met afstand)</option>
            </select>
            <br>
            <br>
            <button type="submit" name="submit" value="voegToe">Voeg contact toe</button><br> <br>
            <button type="submit" name="submit" value="naarWelkom">Ga terug</button>
        </form>
    </body>
</html>

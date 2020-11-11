<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welkom dokters</title>
    </head>
    <body>
        <h1>Welkom dokters</h1>
        <c:if test="${bevestigd == 'ja'}">
                <p style="color:green">Test bevestigd!</p>
                <c:set var = "bevestigd" scope = "session" value = "nee"/>
        </c:if>
        <c:if test="${bevestigd == 'niks'}">
                <p style="color:orange">Vul een test Id in!</p>
                <c:set var = "bevestigd" scope = "session" value = "nee"/>
        </c:if>
                
        <c:if test="${bevestigd == 'double'}">
                <p style="color:red">Geen test gevonden!</p>
                <c:set var = "bevestigd" scope = "session" value = "nee"/>
        </c:if>
        
        <form method="post" action="<c:url value='/controller'/>">
            testId:  <input type="number" name="testId"> <br>
            status:  <select name="status">
                        <option value="negatief">negatief</option>
                        <option value="positief">positief</option>
                        </select> <br>                    
            <button type="submit" name="submit" value="fromWelkomDrs">Update test</button>
        </form><br>
            
        <jsp:include page="/footer.jsp"/>
    </body>
</html>

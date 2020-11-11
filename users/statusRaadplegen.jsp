<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Status Raadplegen</title>
    </head>
    <body>
        <h1>Risico Status</h1>
        <c:if test="${risico == 'LAAG'}">
                <p style="color:green">De risico status van ${currentUsername} is: ${risico}</p>
        </c:if>
        
        <c:if test="${risico == 'HOOG'}">
            <p style="color:red">De risico van ${currentUsername} is: ${risico}</p><br>
            <form method="post" action="<c:url value='controller'/>">
            Klik hier om een test aan te vragen <button type="submit" name="submit" value="testAanvragen">Test aanvragen</button>
            </form> 
        </c:if>
            Ingegeve nauwe contacten: ${nauwCount}<br>
            Ingegeve middelmatige contacten: ${middelmatigCount}<br>
            Ingegeve Veilige contacten: ${veilgCount}<br><br>
        <form method="post" action="<c:url value='/controller'/>">
            <button type="submit" name="submit" value="naarWelkom">Ga terug</button>
        </form><br>
            
     <jsp:include page="/footer.jsp"/>
    </body>
</html>

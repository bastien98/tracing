<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <title>Status Raadplegen</title>
    </head>
    <body>
        <h1 >Risico Status</h1>
        <c:set var="changeSeen" value="no" scope="session"/>
        <c:if test="${risico == 'LAAG'}">
                <p style="color:green">De risico status van ${currentUsername} is: ${risico}</p>
        </c:if>
        
        <c:if test="${risico == 'HOOG'}">
            <p style="color:red">De risico van ${currentUsername} is: ${risico}</p><br>
            <c:if test="${test == 'yes'}">
                <form method="post" action="<c:url value='Controller'/>">
                    Klik hier om een test aan te vragen <button type="submit" name="submit" value="coronaTest">Test aanvragen</button>
                </form> 
            </c:if>
        </c:if>
        
        <c:if test="${test == 'no'}">
            <c:choose>
                <c:when test="${notified == 'no'}">
                    <p style ="color:blue"> Resultaat: In uitvoering </p><br><br>
                    <c:set var="nogInUitvoering" scope="session" value="yes"/>
                </c:when>
                <c:otherwise>
                    <c:set var="changeSeen" value="yes" scope="session"/>
                    <c:set var="nogInUitvoering" scope="session" value="no"/>
                    <c:choose>
                       <c:when test = "${status == 'negatief' && seen == 'no'}">
                            <p style ="color:green"> <c:out value="Het resultaat van de test met nummer ${sessionScope.testId} is : ${sessionScope.status}"/></p>
                             
                        </c:when>
                        <c:otherwise>
                            <p style ="color:red"> <c:out value="Het resultaat van de test met nummer ${sessionScope.testId} is : ${sessionScope.status}"/></p>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:if>
            Ingegeve nauwe contacten: ${nauwCount}<br>
            Ingegeve middelmatige contacten: ${middelmatigCount}<br>
            Ingegeve Veilige contacten: ${veilgCount}<br><br>
        <form method="post" action="<c:url value='/Controller'/>">
            <button type="submit" name="submit" value="naarWelkomFromStatusraadplegen">Ga terug</button>
        </form><br>
            
     <jsp:include page="/footer.jsp"/>
    </body>
</html>

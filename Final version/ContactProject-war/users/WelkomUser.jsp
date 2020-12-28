<%-- 
    Document   : welkom
    Created on : 6-nov-2020, 18:33:27
    Author     : basti
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            #welkom{
                background-color: black;
                color: white;
                height: 50px;
                text-align: center;
                line-height: 50px;
                font-style: italic;
                }

        </style>
        <title>Welcome</title>
    </head>
        <c:set var="name" value="<%=request.getUserPrincipal()%>"></c:set>
        <c:set var="seen" value="no" scope="session"/>
        <c:set var="toRed" value="no" scope="session"/>
        <sql:setDataSource var="db"  driver="org.apache.derby.jdbc.ClientDriver" 
                           url="jdbc:derby://localhost:1527/DataBase"
                           user="karim" password="karim"/>
        
        <sql:query dataSource="${db}" var ="result">
            select * from Tests 
        </sql:query> 
            
        <sql:query  dataSource="${db}" var ="result2">
            SELECT * from History AS fromm, History AS too, Contacts AS c, Tests AS t where fromm.idcontact = too.idcontact and fromm.username != too.username and c.id = fromm.idcontact and too.username = t.username and t.status ='positief' and (c.sort ='nauw' or c.sort ='middelmatig')
        </sql:query> 
        
       
        <c:forEach items="${result2.rows}" var="res2">
           <%-- <c:out value="${res2}"/> --%> <%-- De resultaat van de query result2--%>
            <c:if test="${res2.addedby == name && res2.cseen == 'no'}">
                <c:set var="toRed" value="yes" scope="session"/>
               
            </c:if>
        </c:forEach>
        
        <form method="post" action="<c:url value='/Controller'/>">
            <h1 id="welkom"> Welkom  gebruikers </h1>
            <c:forEach items="${result.rows}" var="res">
                <c:if test="${res.username == name && (res.status =='negatief' || res.status == 'positief') && res.seen=='no'}">
                    <br><p style="color:blue">Het resultaat van de test is beschikbaar. U kunt het zien door op "Status raadplegen" knop te klikken.</p>
                </c:if>
                <c:if test="${res.username == name && res.seen=='no'}">
                    <c:set var="seen" value="yes" scope="session"/>
                </c:if>
            </c:forEach>
               
            <c:if test="${toRed == 'yes'}"> <%-- in naarWelkomFromStatusRaadplegen --%>
                <p style="color:blue">Gelieve een coronatest aan te vragen want één van uw contacten is besmet. </p>
                <p style="color:red">RISICO IS HOOG!</p>
            </c:if>   
           <%-- <c:set var="toRed" value="no"/> --%>
            
           <c:if test="${status == 'updated'}">
                <p style="color:green">Contact is upgedate.</p>
            </c:if>
            <c:if test="${status == 'new'}">
                <p style="color:green">Nieuw contact is geregistreerd.</p>
            </c:if>
                <button type="submit" name="submit" value="registreer">registreer een contact die je hebt gehad.</button><br><br>
                <button type="submit" name="submit" value="statusRaadplegen">Status raadplegen</button><br><br>
            
               <%-- <c:if test="${sessionScope.test == 'yes'}"> --%>
               
               <c:if test="${seen == 'no'}">
                    <button type="submit" name="submit" value="coronaTest">Corona Test</button><br><br>
                </c:if> 
                    <%-- </c:if> --%>
                <%-- <c:if test="${seen == 'no'}"> </c:if> --%>
        </form><br>
         <jsp:include page="/footer.jsp"/>
                
            
                    
               
    
</html>

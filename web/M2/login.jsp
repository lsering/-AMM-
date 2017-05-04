<%-- 
    Document   : profilo
    Created on : Apr 28, 2017, 7:20:02 PM
    Author     : Luigi Serreli
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>NerdBooks: ConfermaPost</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Luigi Serreli">
        <meta name="keywords" content="Book">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head> 
    <body>
        <jsp:include page="header.jsp"/>
        <div id="divBody">
            <jsp:include page="sidebar.jsp"/>
            <div id="content">
                <!--CONTROLLO CHE L'UTENTE NON SIA GIA' LOGGATO. NEL CASO LO REINDIRIZZO DIRETTAMENTE-->
                <%
                   HttpSession sessione = request.getSession();
                   if (sessione.getAttribute("log") != null &&
            sessione.getAttribute("log").equals(true)) {
            request.getRequestDispatcher("/M2/profilo.jsp").forward(request, response);
            return;
        }%>
                
                <c:if test="${invalidData == true}">
                    <div id="InvalidData">I dati inseriti non sono corretti</div>
                </c:if>
                <c:if test="${ErrorLog == true}">
                    <div id="InvalidData">ACCESSO NEGATO.EFFETTUA IL LOGIN</div>
                </c:if>
            <div id="log">
                    <form method="post" action="Login" >

                        <label for="user">Username</label>
                        <div>
                            <input type="Text" name="userN" id="user" />
                        </div>
                        <label for="pass">Password</label>
                        <div>
                            <input type="Password" name="pass" id="pass" />
                        </div>
                        <button type="submit">Invia</button>
                    </form>
                </div>
            </div>
         </div>
    </body>
</html>
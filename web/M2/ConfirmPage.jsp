<%-- 
    Document   : profilo
    Created on : Apr 28, 2017, 7:20:02 PM
    Author     : Luigi Serreli
--%>
<%@page import="amm.nerdbook.Classi.User"%>
<%@page import="amm.nerdbook.Classi.UserFactory"%>
<%@page import="amm.nerdbook.Classi.GroupsFactory"%>
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
                <c:if test ="${log == true}">

                
                </c:if>
                <c:if test="${invalidData == true}">
                    <div id="InvalidData">I dati inseriti non sono corretti</div>
                </c:if>
                    <div>
                        <div id="send_post">
                        
                            <div id="infoPost">
                                 <span class="infoPost">MITTENTE:</span>${mit.name}<br>
                                 <c:if test="${is_bacheca == 1}">
                                  <span class="infoPost">DESTINATARIO:</span>${dest.name}</div>
                                  <form method="post" action="SendPost">
                                        <input type="hidden" name="visit_user" value="${dest.id}">
                                        <input type="hidden" name="textPost" value="${testo}">
                                 </c:if>
                                 <c:if test="${is_bacheca ==0}">
                                  <span class="infoPost">DESTINATARIO:</span>${dest.groupName}</div>
                                  <form method="post" action="SendPost">
                                         <input type="hidden" name="visit_group" value="${dest.id}">
                                        <input type="hidden" name="textPost" value="${testo}">
                                 </c:if>
                    
                                    <button type="submit">Invia</button>
                                  <form method="post" action="Bacheca">
                                    <button type="submit">Cancella</button>
                                  </form>
                        </div>
                </div>
            </div>
         </div>
    </body>
</html>
<%-- 
    Document   : bacheca
    Created on : May 1, 2017, 8:41:06 PM
    Author     : Luigi Serreli
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
       
<!DOCTYPE html>
<html>
    <head>
        <title>NerdBooks: Bacheca</title>
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
                 <c:forEach var ="post" items="${posts}">
                    <div class="posted">
                      <div class="postedcontent">
                        <div class="profile">
                            <div class="profileimg">
                                <img src="${user.urlImmagineProfilo}" width="100" height="100" alt="Immagineprofilo">  
                            </div>    
                            <div class="nameanddate">
                                <div class="icon">
                                    <h4>${user.name}</h4></div>
                                <div class="datepost">${post.data}</div>
                            </div>
                        </div>
                            <div>
                            <p>${post.content}</p>
                            <c:if test ="${post.att.type != -1}">
                               <div class="allegati">
                                   <c:if test ="${post.att.type == 0}"> <!--SE E' un link -->
                                      <a href="${post.att.content}" target="_blank" >LINK</a>
                                   </c:if>
                                   <c:if test ="${post.att.type == 1}"> <!--SE E' un immagine -->
                                      <img src="${post.att.content}" alt="allegato" >
                                   </c:if>
                               </div>  
                            </c:if>
                        </div>
                      </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>

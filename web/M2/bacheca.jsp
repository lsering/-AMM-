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
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/javascript.js"></script>
    </head> 
    <body>
        <c:set var="page" value="bacheca" scope="request"/>
        <jsp:include page="header.jsp"/>
        <div id="divBody">
            <jsp:include page="sidebar.jsp"/>
            <div id="content">
                <c:if test ="${visit_group ==null}">
                    <div id="profileTitle"><h4>Bacheca di ${user.name}</h4></div>
                </c:if>
                <c:if test ="${visit_group != null}">
                     <div id="profileTitle"><h4>Bacheca di ${group.groupName}</h4></div>
                </c:if>
                <div id="send_post">
                   <c:if test ="${visit_user == null && visit_group == null}"> 
                       <form method="post" action="ConfirmPage.jsp?bacheca=0"><!--Devo mandare un post nella mia bacheca-->
                   </c:if>
                   <c:if test ="${visit_user != null}">  <!--Devo mandare un post alla bacheca di un followed-->
                    <form method="post" action="ConfirmPage.jsp?bacheca=${visit_user}">
                    </c:if>
                   <c:if test ="${visit_group != null}">  <!--Devo mandare un post ad un gruppo-->
                    <form method="post" action="ConfirmPage.jsp?gruppo=${visit_group}">
                    </c:if>
                        <div><label for="textPost" id="prova">A cosa stai pensando?</label>
                              <textarea name="textPost" id="textPost"></textarea>
                             <div><input type="file" id="load" name="load"></div>
                        </div>
                        <input type="submit">                        
                    </form>
                </div>
                 <c:forEach var ="post" items="${posts}">
                    <div class="posted">
                      <div class="postedcontent">
                        <div class="profile">
                            <div class="profileimg">
                                <img src="${post.user.urlImmagineProfilo}" width="100" height="100" alt="Immagineprofilo">  
                            </div>    
                            <div class="nameanddate">
                                <div class="icon">
                                    <h4>${post.user.name}</h4></div>
                                <div class="datepost">${post.data}</div>
                            </div>
                        </div>
                            <div>
                            <p>${post.content}</p>
                            <c:forEach var ="att" items="${post.att}"> <!--Itero gli allegati che potrebbero essere più di uno-->
                               <div class="allegati">
                                   <c:if test ="${att.type == 0}"> <!--SE E' un link -->
                                      <a href="/${att.content}" target="_blank" >LINK</a>
                                   </c:if>
                                   <c:if test ="${att.type == 1}"> <!--SE E' un immagine -->
                                      <img src="${att.content}" alt="allegato" >
                                   </c:if>
                               </div>  
                                 </c:forEach>
                            </div>
                      </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>

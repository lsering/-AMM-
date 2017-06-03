<%-- 
    Document   : divBody
    Created on : Apr 28, 2017, 7:27:49 PM
    Author     : Luigi Serreli
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <div id="sidebar1">
     <c:if test ="${page == 'profilo'}">
                <div id="favorite">
                    <h3>Dati:</h3>
                    <ol>
                        <li>Nome: ${user.name}</li>
                        <li>Cognome:${user.surname}</li>
                        <li>Frase:${user.frase}</li>
                        <li>Email:<br>${user.email}</li>
                    </ol>
                </div>
     </c:if>
     <c:if test ="${page == 'bacheca'}">
                <div id="favorite">
                    <h3>Seguiti:</h3>
                    <input class="searchUsers"  placeholder="Cerca amici" name="ricerca" id="searchYourFriend" >
                        <button id="searchYourFriendByClick">Cerca</button>
                   <div id="utenti"> <ol>
                    <c:forEach var ="utente" items="${users}">
                        <li><div class="icon"><a href="Bacheca?visit_user=${utente.id}">${utente.name} ${utente.surname}</a></div></li>
                     
                </c:forEach>
 
                        </div>          
                </div>
                <div id="groups">
        <h3>Gruppi:</h3>
         <ol>
            <c:forEach var ="gruppo" items="${groups}">
                <li><div class="icon"><a href="Bacheca?visit_group=${gruppo.id}">${gruppo.groupName}</a></div></li>
            </c:forEach>
         </ol>
     </div>
     </c:if>
     
                      
</div>
 
 
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
                    <h3>Utenti:</h3>
                    <ol>
                    <c:forEach var ="utente" items="${users}">
                        <li><div class="icon"><a href="/AMM/M2/Bacheca?visit_user=${utente.id}">${utente.name} ${utente.surname}</a></div></li>
                     
                </c:forEach>

                </div>
                          </c:if>
                <div id="groups">
                    <h3>Gruppi:</h3>
                    <ol>
                        <li><div class="icon">Gli sfigati</div></li>
                        <li><div class="icon">Gruppo di studio</div></li>
                    </ol>
                </div>
                      
            </div>
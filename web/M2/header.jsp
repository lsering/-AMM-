<%-- 
    Document   : header
    Created on : Apr 28, 2017, 7:16:46 PM
    Author     : Luigi Serreli
--%>
<%@page import="amm.nerdbook.Classi.User"%>
<%@page import="amm.nerdbook.Classi.UserFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <header>
            <div id="headtitle">
                <span id="navtitle">Nerdbook</span>
            </div>
            <nav>
                <ol>
                    <li <c:if test ="${page == 'login'}">class="active"</c:if>><a href="Login">Login</a></li>
                    <li <c:if test ="${page == 'bacheca'}">class="active"</c:if>><a href="Bacheca">Bacheca</a></li>
                    <li <c:if test ="${page == 'profilo'}">class="active"</c:if>>
                        <a href="profilo.html">MyProfile</a></li>
                    <li><a href="../">Descrizione</a></li>
                </ol>  
            </nav>
            <div id="blocklogin">
            <c:if test ="${log == true}">
                <div id="logoutblock">
                    <img src="${user_logged.urlImmagineProfilo}" width="20" height="20" alt="ImmagineProfilo">
                    <h4>${user_logged.name}</h4>
                    <a href="Login?log-out=1">Logout</a>
                </div>
            </c:if>
            <c:if test ="${log != true}">
                <span id="login"><a href="login.jsp">Utente non riconosciuto.<br>Esegui il Login</a></span>
            </c:if>
                    
            </div>
        </header>

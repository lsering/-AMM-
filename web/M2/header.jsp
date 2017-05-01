<%-- 
    Document   : header
    Created on : Apr 28, 2017, 7:16:46 PM
    Author     : Luigi Serreli
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <header>
            <div id="headtitle">
                <span id="navtitle">Nerdbook</span>
            </div>
            <nav>
                <ol>
                    <li><a href="login.html">Login</a></li>
                    <li><a href="bacheca.html">Bacheca</a></li>
                    <li class="active"><a href="profilo.html">MyProfile</a></li>
                    <li><a href="descrizione.html">Descrizione</a></li>
                </ol>  
            </nav>
            <div id="blocklogin">
                <!--CONTROLLO SESSIONE-->
                <div id="logoutblock">
                    <img src="img/Tesla.jpg" width="20" height="20" alt="Immagineprofilo1">
                    <h4>${user.name}</h4>
                    <a href="login.html">Logout</a>
                </div>
            </div>
        </header>

<%-- 
    Document   : descrizione.jsp
    Created on : Jun 8, 2017, 10:19:36 AM
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
        <link rel="stylesheet" type="text/css" href="M2/style.css" media="screen">
    </head> 
    <body>
        <header>
         <div id="headtitle">
                <span id="navtitle">Nerdbook</span>
            </div>
            <nav>
                <ol>
                    <li><a href="M2/Login">Login</a></li>
                    <li><a href="M2/Bacheca">Bacheca</a></li>
                    <li><a href="M2/profilo.html">MyProfile</a></li>
                    <li><a href="" class="active">Descrizione</a></li>
                </ol>  
            </nav>
            <div id="blocklogin">
                <div id="logoutblock">
                    <p>Benvenuto su Nerdbook</p>
                </div>
            </div>
        </header>
        <div id="divBody">
            <div id="sidebar1">
               
            </div>
            <div id="content">
    <div id="desc_content">
                    <h2>Domende comuni</h2>
                    <h4>Sommario:</h4>
                    <ul>
                        <li><a href="#segnalibro1"><h4>Il sito è a pagamento?</h4></a></li>
                        <li><a href="#segnalibro2"><h4>A chi è dedicato?</h4></a></li>
                        <li><a href="#segnalibro3"><h4>Come puoi contattarci?</h4></a></li>
                    </ul>
                    <ul>
                        <li><a id="segnalibro1"><h4>Il sito è a pagamento?</h4></a><p>Si,l'abbonamento ha un costo di 200€/mese</p></li>
                        <li><a id="segnalibro2"><h4>A chi è dedicato il sito?</h4></a><p>A tutte le persone</p></li>
                        <li><a id="segnalibro3"><h4>Come puoi contattarci?</h4></a><p>Invia una mail all'indirizzo:nerbook@nerd.com</p></li>
                    </ul>

                </div>
            </div>
        </div>
    </body>
</html>


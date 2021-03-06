<%-- 
    Document   : profilo
    Created on : Apr 28, 2017, 7:20:02 PM
    Author     : Luigi Serreli
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
        <title>NerdBook: Profilo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Luigi Serreli">
        <meta name="keywords" content="Book">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head> 
    <body>
        <c:set var="page" value="profilo" scope="request"/>
        <jsp:include page="header.jsp"/>
         <div id="divBody">
        <jsp:include page="sidebar.jsp"/>
         <div id="content">
                <div id="contentidentity">
                    <form method="post" action="profilo.html">
                        <div id="img">
                            <div>
                                <label for="imgPost">
                                    <img src="${user.urlImmagineProfilo}" alt="immprofilo" width="100" height="100">
                                </label>
                            </div>
                            <div>
                                <input type="file" name="imgPost" id="imgPost">
                            </div>
                            
                        </div>
                        <div id="dataprofile">

                            <div id="name"><label for="nome">Nome:</label>
                                <input type="text" id="nome" name="nome" />
                            </div>
                            <div id="surname"><label for="cognome">Cognome:</label>
                                <input type="text" id="cognome" name="cognome" />
                            </div>
                            <div id="presentation"><label for="frase_p">Frase:</label>
                                <input type="text" id="frase_p" name="presentazione" />
                            </div>
                            <div id="data">
                                <label for="email">Email:</label>
                                <input type="text" id="email" name="email" />
                            </div>
                            <div id="insertpass">
                                <label for="pwd">Password:</label>
                                <input type="password" id="pwd" name="password" />

                                <label for="cpwd">Conferma:</label>
                                <input type="password" id="cpwd" name="confpassword" />
                            </div>
                            <div>
                                <input type="hidden" name="update" value="1">
                                <button type="submit">Invia</button>
                                
                            </div>
                    </form>
                                <form method="post" action="profilo.html">
                            <div>
                                <input type="hidden" name="delete" value="1">
                                <button type="submit">Elimina il mio account </button>
                                    <c:if test="${Errordel == true}">
                          <div>Errore cancellazione</div>
                          </c:if>          
                            </div>
                      <c:if test="${ErrorData == true}">
                    <div id="InsData">Errore inserimento</div>
                      </c:if>
                    <c:if test="${ErrorData == false}">
                    <div id="InsData">Dati inseriti con successo</div>
                    </c:if>
                    
                        </div>
                    </form>
                </div>

            </div>
         </div>
    </body>
</html>

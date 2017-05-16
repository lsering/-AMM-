<%-- 
    Document   : profilo
    Created on : Apr 28, 2017, 7:20:02 PM
    Author     : Luigi Serreli
--%>
<%@page import="amm.nerdbook.Classi.User"%>
<%@page import="amm.nerdbook.Classi.UserFactory"%>
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
                    String mit, dest;
                    int flag=0;
                    if(sessione==null && sessione.getAttribute("log")==null &&
              sessione.getAttribute("log").equals(false)){
                    flag = 1;
                    }
                      String idBacheca =request.getParameter("bacheca");
                      String text = request.getParameter("textPost");
                      int idmittente = (Integer)sessione.getAttribute("user_id");
                      int idB=idmittente;
                      UserFactory mu = UserFactory.getInstance();
                    User u = mu.getUserById(idmittente);
                    mit = u.getName() + " " + u.getSurname();
                    dest = mit;
                    if(idBacheca != "0") //Quando idBacheca == 0 --> sta scrivendo nella sua bacheca
                    {
                        idB = Integer.parseInt(idBacheca);
                        u = mu.getUserById(idB);
                        try{dest = u.getName() + " " + u.getSurname();}catch(Exception exc){}
                    }
                 
                     %> 
                <c:if test="${invalidData == true}">
                    <div id="InvalidData">I dati inseriti non sono corretti</div>
                </c:if>
                    <div>
                        <div id="send_post">
                            <div id="infoPost"><span class="infoPost">MITTENTE:</span><%=mit%><br>
                                  <span class="infoPost">DESTINATARIO:</span><%=dest%></div>
                                  <form method="post" action="SendPost?visit_user=<%=idBacheca%>&textPost=<%=text%>" >
                                    <button type="submit">Invia</button>
                                  </form>
                                  <form method="post" action="Bacheca">
                                    <button type="submit">Cancella</button>
                                  </form>
                        </div>
                </div>
            </div>
         </div>
    </body>
</html>
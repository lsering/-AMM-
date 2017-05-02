<%-- 
    Document   : header
    Created on : Apr 28, 2017, 7:16:46 PM
    Author     : Luigi Serreli
--%>
<%@page import="amm.nerdbook.Classi.User"%>
<%@page import="amm.nerdbook.Classi.MakeUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <header>
            <div id="headtitle">
                <span id="navtitle">Nerdbook</span>
            </div>
            <nav>
                <ol>
                    <li><a href="login.html">Logino</a></li>
                    <li><a href="Bacheca">Bacheca</a></li>
                    <li class="active"><a href="profilo.html">MyProfile</a></li>
                    <li><a href="descrizione.html">Descrizione</a></li>
                </ol>  
            </nav>
            <div id="blocklogin">
                <div id="logoutblock">
                <!--CONTROLLO SESSIONE-->
                <%
                HttpSession s = request.getSession(false);
                int logged = 0;
           if(s!=null && s.getAttribute("log")!=null &&
              s.getAttribute("log").equals(true)){
                MakeUser mu = new MakeUser();
                int id = (Integer)s.getAttribute("user_id");
                User u = mu.getUserById(id);
                out.println("<img src='"+u.getUrlImmagineProfilo()+"' width='20' height='20' alt='ImmagineProfilo'");
               out.println("<h4>"+u.getName()+"</h4>");
               out.println("<a href='login.html'>Logout</a>");
}%>
                </div>
            </div>
        </header>

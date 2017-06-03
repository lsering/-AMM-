<%-- 
    Document   : userlist
    Created on : May 31, 2017, 12:48:04 PM
    Author     : Luigi Serreli
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<json:array>
    <c:forEach var="user" items="${listaUtenti}">
        <json:object>
            <json:property name="id" value="${user.id}"/>
            <json:property name="name" value="${user.name}"/>
            <json:property name="surname" value="${user.surname}"/>
        </json:object>
    </c:forEach>
</json:array>

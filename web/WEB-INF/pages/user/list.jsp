<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 10.12.2020
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
<h1>User List</h1>
<c:forEach var="user" items="${users}">
    <c:url var="userEditUrl" value="/user/edit.html">
        <c:param name="id" value="${user.id}"/>
    </c:url>
    <li><a href="${userEditUrl}">${user.login}</a> [${user.role}]</li>
</c:forEach>
<c:url var="userEditUrl" value="/user/edit.html"/>
<a href="${userEditUrl}">Добавить юзера</a>
</body>
</html>

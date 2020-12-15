<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 14.12.2020
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${not empty user}">
        <c:set var="title" value="Редактирование пользователя ${user.login}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Создание пользователя"/>
    </c:otherwise>
</c:choose>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>
<c:url var="userSaveUrl" value="/user/save.html"/>
<form action="${userSaveUrl}" method="post">
    <p>Имя пользователя:
        <input type="text" name="login" value="${user.login}"></p>
    <p>Пароль:
        <input type="text" name="password" value="${user.password}"></p>
    <p>E-mail:
        <input type="text" name="mail" value="${user.mail}"></p>
    <button type="submit">Сохранить</button>
</form>
</body>
</html>

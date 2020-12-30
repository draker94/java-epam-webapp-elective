<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 24.12.2020
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Страница авторизации</title>
</head>
<c:if test="${not empty param.message}">
    <p>${param.message}</p>
</c:if>
<body>
<h1>Страница авторизации</h1>
<c:url var="loginUrl" value="/main/login.html"/>
<form action="${loginUrl}" method="post">
    <p>Имя пользователя:
        <input type="text" name="login">
    </p>
    <p>Пароль:
        <input type="password" name="password">
    </p>
    <button type="submit">Войти</button>
</form>
</body>
</html>
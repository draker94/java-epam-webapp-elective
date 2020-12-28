<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 10.12.2020
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tag/contains-function.tld" prefix="fn" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список пользователей</title>
</head>
<c:url var="userDeleteUrl" value="/user/delete.html"/>
<form action="${userDeleteUrl}" method="post">
    <table border="1">
        <caption>Список пользователей</caption>
        <tr>
            <th>Удалить</th>
            <th>Редактивать</th>
            <th>ID</th>
            <th>Логин</th>
            <th>Пароль</th>
            <th>E-mail</th>
            <th>Роль</th>
        </tr>
        <c:forEach var="user" items="${users}">
        <c:url var="userEditUrl" value="/user/edit.html">
            <c:param name="id" value="${user.id}"/>
        </c:url>
        <c:set var="contains" value="${false}"/>
        <c:if test="${fn:contains(freeUsers, user)}">
            <c:set var="contains" value="${true}"/>
        </c:if>
        <tr>
            <td><input type="checkbox" ${contains ? "" : "disabled=&quot;&quot;"} name="id" value="${user.id}"></td>
            <td><a href="${userEditUrl}">ред.</a></td>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.mail}</td>
            <td>${user.role.name}</td>
            </c:forEach>
        </tr>
    </table>
    <button type="submit">Удалить</button>
</form>
<c:url var="userEditUrl" value="/user/edit.html"/>
<a href="${userEditUrl}">Добавить пользователя</a>
</body>
</html>
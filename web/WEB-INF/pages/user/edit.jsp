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
     <c:if test="${not empty user}">
        <input type="hidden" name="id" value="${user.id}">
    </c:if>
    <p>Имя пользователя:
        <input type="text" name="login" value="${user.login}"></p>
    <p>Пароль:
        <input type="text" name="password" value="${user.password}"></p>
    <p>E-mail:
        <input type="text" name="mail" value="${user.mail}"></p>
    <p>Роль:
            <select name="role">
                <c:forEach var="role" items="${roles}">
                    <c:choose>
                        <c:when test="${role == user.role}">
                            <c:set var="selected" value="selected"/>
                        </c:when>
                        <c:otherwise>
                            <c:remove var="selected"/>
                        </c:otherwise>
                    </c:choose>
                    <option value="${role}"${selected}>${role.name}</option>
                </c:forEach>
            </select>
    </p>
    <button type="submit">Сохранить</button>
</form>
</body>
</html>

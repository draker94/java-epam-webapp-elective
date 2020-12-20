<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 14.12.2020
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="instructorSaveUrl" value="/instructor/save.html">
    <c:param name="isNewInstructor" value="${(not empty freeUsersList) ? true : false}"/>
</c:url>
<c:choose>
    <c:when test="${not empty instructor}">
        <c:set var="title" value="Редактирование профиля преподавателя ${instructor.name} ${instructor.surname}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Создание профиля преподавателя"/>
    </c:otherwise>
</c:choose>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>
<form action="${instructorSaveUrl}" method="post">
     <c:if test="${not empty instructor}">
        <input type="hidden" name="id" value="${instructor.id}">
    </c:if>
    <p>Имя преподавателя:
        <input type="text" name="name" value="${instructor.name}"></p>
    <p>Фамилия преподавателя:
        <input type="text" name="surname" value="${instructor.surname}"></p>
    <p>Учёное звание:
            <select name="rank">
                <c:forEach var="rank" items="${ranks}">
                    <c:choose>
                        <c:when test="${rank == instructor.rank}">
                            <c:set var="selected" value="selected"/>
                        </c:when>
                        <c:otherwise>
                            <c:remove var="selected"/>
                        </c:otherwise>
                    </c:choose>
                    <option value="${rank}" ${selected}>${rank.name}</option>>
                </c:forEach>>
            </select>
    </p>
    <c:if test="${not empty freeUsersList}">
        <select name="id">
            <c:forEach var="user" items="${freeUsersList}">
                <option value="${user.id}">${user.id} ${user.login} [${user.role}]</option>
            </c:forEach>
        </select>
    </c:if>
    <button type="submit">Сохранить</button>
</form>
</body>
</html>

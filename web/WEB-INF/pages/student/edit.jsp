<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="canSave" value="${true}"/>
<c:url var="studentSaveUrl" value="/student/save.html">
    <c:param name="isNewStudent" value="${(not empty freeUsersList) ? true : false}"/>
</c:url>
<c:choose>
    <c:when test="${not empty student}">
        <c:set var="title" value="Редактирование профиля студента ${student.name} ${student.surname}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Создание профиля студента"/>
    </c:otherwise>
</c:choose>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>
<form action="${studentSaveUrl}" method="post">
    <c:if test="${not empty student}">
        <input type="hidden" name="id" value="${student.id}">
    </c:if>
    <p>Имя студента:
        <input type="text" name="name" value="${student.name}"></p>
    <p>Фамилия студента:
        <input type="text" name="surname" value="${student.surname}"></p>
    <p>Курс:
        <select name="studyYear">
            <c:forEach var="i" begin="1" end="7" step="1">
                <c:choose>
                    <c:when test="${i == student.studyYear}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${i}" ${selected}>${i}</option>
            </c:forEach>
        </select>
    </p>
    <c:if test="${empty student}">
        <c:choose>
            <c:when test="${empty freeUsersList}">
                <p>Нет аккаунтов для создания профиля!</p>
                <c:set var="canSave" value="${false}"/>
            </c:when>
            <c:otherwise>
                <select name="id">
                    <c:forEach var="user" items="${freeUsersList}">
                        <option value="${user.id}">${user.id} ${user.login} [${user.role}]</option>
                    </c:forEach>
                </select>
            </c:otherwise>
        </c:choose>
    </c:if>>
    <button ${canSave ? ""  : "disabled=&quot;&quot;"} type="submit">Сохранить</button>
</form>
<c:url var="back" value="/student/list.html"/>
<a href="${back}">Назад</a>
</body>
</html>

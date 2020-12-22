<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tag/contains-function.tld" prefix="fn" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список курсов</title>
</head>
<body>
<h1>Список курсов</h1>
<c:url var="courseDeleteUrl" value="/course/delete.html"/>
<form action="${courseDeleteUrl}" method="post">
    <c:forEach var="entry" items="${courseInstructorMap}">
        <c:url var="courseEditUrl" value="/course/edit.html">
            <c:param name="id" value="${entry.key.id}"/>
        </c:url>
        <c:set var="contains" value="${false}"/>
        <c:if test="${fn:contains(freeCourses, entry.key)}">
            <c:set var="contains" value="${true}"/>
        </c:if>
        <li><input type="checkbox" ${contains ? "" : "disabled=&quot;&quot;"} name="id" value="${entry.key.id}">
            <a href="${courseEditUrl}">${entry.key.name}</a> ${entry.value.surname} ${entry.value.name} Длительность курса - ${entry.key.hours} часов
            <details>${entry.key.description}</details>
        </li>
    </c:forEach>
    <c:url var="courseEditUrl" value="/course/edit.html"/>
    <a href="${courseEditUrl}">Создать новый курс</a>
    <p><button type="submit">Удалить</button></p>
</form>
</body>
</html>
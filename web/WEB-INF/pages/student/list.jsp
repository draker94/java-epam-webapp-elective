<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tag/contains-function.tld" prefix="fn" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список студентов</title>
</head>
<body>
<h1>Список студентов</h1>
<c:url var="studentDeleteUrl" value="/student/delete.html"/>
<form action="${studentDeleteUrl}" method="post">
    <c:forEach var="student" items="${students}">
        <c:url var="studentEditUrl" value="/student/edit.html">
            <c:param name="id" value="${student.id}"/>
        </c:url>
        <c:set var="contains" value="${false}"/>
        <c:if test="${fn:contains(freeStudents, student)}">
            <c:set var="contains" value="${true}"/>
        </c:if>
        <li><input type="checkbox" ${contains ? "" : "disabled=&quot;&quot;"} name="id" value="${student.id}">
            <a href="${studentEditUrl}">${student.name} ${student.surname}</a></li>
    </c:forEach>
    <c:url var="studentEditUrl" value="/student/edit.html"/>
    <a href="${studentEditUrl}">Добавить студента</a>
    <button type="submit">Удалить</button>
</form>
</body>
</html>

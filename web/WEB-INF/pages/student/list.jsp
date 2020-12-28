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
<c:url var="studentDeleteUrl" value="/student/delete.html"/>
<form action="${studentDeleteUrl}" method="post">
    <table border="1">
        <caption>Список студентов</caption>
        <tr>
            <th>Удалить</th>
            <th>Редактивать</th>
            <th>ID пользователя</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Курс</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <c:url var="studentEditUrl" value="/student/edit.html">
                <c:param name="id" value="${student.id}"/>
            </c:url>
            <c:set var="contains" value="${false}"/>
            <c:if test="${fn:contains(freeStudents, student)}">
                <c:set var="contains" value="${true}"/>
            </c:if>
            <tr>
                <td><input type="checkbox" ${contains ? "" : "disabled=&quot;&quot;"} name="id" value="${student.id}">
                </td>
                <td><a href="${studentEditUrl}">ред.</a></td>
                <td>${student.id}</td>
                <td>${student.surname}</td>
                <td>${student.name}</td>
                <td>${student.studyYear}</td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit">Удалить</button>
</form>
<c:url var="studentEditUrl" value="/student/edit.html"/>
<a href="${studentEditUrl}">Добавить студента</a>
</body>
</html>

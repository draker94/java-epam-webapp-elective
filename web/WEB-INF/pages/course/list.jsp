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
<c:url var="courseDeleteUrl" value="/course/delete.html"/>
<form action="${courseDeleteUrl}" method="post">
    <table border="1">
        <caption>Список курсов</caption>
        <tr>
            <th>Удалить</th>
            <th>Редактивать</th>
            <th>Название</th>
            <th>Колличество часов</th>
            <th>Преподаватель</th>
            <th>Описание</th>
        </tr>
        <c:forEach var="course" items="${coursesList}">
            <c:url var="courseEditUrl" value="/course/edit.html">
                <c:param name="id" value="${course.id}"/>
            </c:url>
            <c:set var="contains" value="${false}"/>
            <c:if test="${fn:contains(freeCourses, course)}">
                <c:set var="contains" value="${true}"/>
            </c:if>
            <tr>
                <td><input type="checkbox" ${contains ? "" : "disabled=&quot;&quot;"} name="id" value="${course.id}">
                </td>
                <td><a href="${courseEditUrl}">ред.</a></td>
                <td>${course.name}</td>
                <td>${course.hours}</td>
                <td>${course.instructor.surname} ${course.instructor.name}</td>
                <td>${course.description}</td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit">Удалить</button>
</form>
<c:url var="courseEditUrl" value="/course/edit.html"/>
<a href="${courseEditUrl}">Создать новый курс</a>
</body>
</html>
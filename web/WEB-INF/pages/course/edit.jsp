<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="courseSaveUrl" value="/course/save.html"/>
<c:choose>
    <c:when test="${not empty course}">
        <c:set var="title" value="Редактирование курса ${course.name}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Создание нового курса"/>
    </c:otherwise>
</c:choose>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>
<form action="${courseSaveUrl}" method="post">
    <c:if test="${not empty course}">
        <input type="hidden" name="id" value="${course.id}">
    </c:if>
    <p>Название курса:
        <input type="text" name="name" value="${course.name}"></p>
    <p>Преподаватель:
        <select name="instructorId">
            <c:forEach var="instructor" items="${instructorList}">
                <c:choose>
                    <c:when test="${course.instructor.id == instructor.id}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${instructor.id}" ${selected}>${instructor.name} ${instructor.surname}</option>
            </c:forEach>
        </select>
    <p>Колличество часов:
        <input type="number" max="256" name="hours" value="${course.hours}"></p>
    </p>
    <p>Описание:
        <input type="text" size="100" maxlength="512" name="description" value="${course.description}"></p>
    <button type="submit">Сохранить</button>
</form>
<c:url var="back" value="/course/list.html"/>
<a href="${back}">Назад</a>
</body>
</html>

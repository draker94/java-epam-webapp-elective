<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 25.12.2020
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tag/contains-function.tld" prefix="fn" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список записанных на курсы</title>
</head>
<body>
<h1>Список записанных на курсы</h1>
<c:url var="assignmentDeleteUrl" value="/assignment/delete.html"/>
<form action="${assignmentDeleteUrl}" method="post">
    <c:forEach var="assignment" items="${assignmentList}">
        <c:url var="assignmentEditUrl" value="/assignment/edit.html">
            <c:param name="id" value="${assignment.id}"/>
        </c:url>
        <c:set var="contains" value="${false}"/>
        <c:if test="${fn:contains(freeAssignments, assignment)}">
            <c:set var="contains" value="${true}"/>
        </c:if>
        <li><input type="checkbox" ${contains ? "" : "disabled=&quot;&quot;"} name="id" value="${assignment.id}">
            <a href="${assignmentEditUrl}">ред. </a>
            Студент - ${assignment.student.surname} ${assignment.student.name} Записан на курс: ${assignment.course.name}
            Начало обучения - ${assignment.beginDate}, конец ${assignment.endDate}
        </li>
    </c:forEach>
    <c:url var="assignmentEditUrl" value="/assignment/edit.html"/>
    <a href="${assignmentEditUrl}">Добавить новый курс обучения для студента</a>
    <p>
        <button type="submit">Удалить</button>
    </p>
</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 10.12.2020
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Instructors List</title>
</head>
<body>
<h1>Instructors List</h1>
<c:forEach var="instructor" items="${instructors}">
    <c:url var="instructorEditUrl" value="/instructor/edit.html">
        <c:param name="id" value="${instructor.id}"/>
    </c:url>
    <li><a href="${instructorEditUrl}">${instructor.name} ${instructor.surname}</a></li>
</c:forEach>
<c:url var="instructorEditUrl" value="/instructor/edit.html"/>
<a href="${instructorEditUrl}">Добавить преподавателя</a>
</body>
</html>

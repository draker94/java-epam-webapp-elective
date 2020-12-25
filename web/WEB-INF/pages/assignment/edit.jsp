<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 25.12.2020
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="create" value="${empty assignment}"/>
<c:choose>
    <c:when test="${not create}">
        <c:set var="title" value="Редактирование записи на курс для ${assignment.student.surname} ${assignment.student.name}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Создание новой записи на курс"/>
    </c:otherwise>
</c:choose>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>
<c:url var="assignmentSaveUrl" value="/assignment/save.html"/>
<form action="${assignmentSaveUrl}" method="post">
    <c:if test="${not create}">
        <input type="hidden" name="id" value="${assignment.id}">
        <input type="hidden" name="studentId" value="${assignment.student.id}">
    </c:if>
    <p> Студент:
        <select name="studentId" ${create ? ""  : "disabled=&quot;&quot;"}>
            <c:forEach var="student" items="${studentList}">
                <c:choose>
                    <c:when test="${assignment.student.id == student.id}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${student.id}" ${selected}>${student.name} ${student.surname}</option>
            </c:forEach>
        </select>

    </p>
    <p>Курс:
        <select name="courseId">
            <c:forEach var="course" items="${courseList}">
                <c:choose>
                    <c:when test="${assignment.course.id == course.id}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${course.id}" ${selected}>${course.name}</option>
            </c:forEach>
        </select>
    </p>
    <p>Дата начала:
        <input type="date" name="beginDate" value="${assignment.beginDate}">
    </p>
    <p>Дата окончания:
        <input type="date" name="endDate" value="${assignment.endDate}">
    </p>
    <button type="submit">Сохранить</button>
</form>
<c:url var="back" value="/assignment/list.html"/>
<a href="${back}">Назад</a>
</body>
</html>

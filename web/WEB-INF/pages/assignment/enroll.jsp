<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 02.01.2021
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag" %>
<tag:head title="Запись на курс для ${student.surname} ${student.name}">
    <c:url var="assignmentSaveUrl" value="/assignment/save.html"/>
    <form action="${assignmentSaveUrl}" method="post">
        <input type="hidden" name="studentId" value="${student.id}">
        <p> Студент: ${student.surname} ${student.name}</p>
        <p>Выберите курс, на который хотите записаться:
            <select name="courseId">
                <c:forEach var="course" items="${courseList}">
                        <option value="${course.id}">${course.name}</option>
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
    <c:url var="back" value="/course/list.html"/>
    <a href="${back}">Назад</a>
</tag:head>
<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 25.12.2020
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag" %>
<tag:head title="Записи на курсы">
    <c:url var="assignmentDeleteUrl" value="/assignment/delete.html"/>
    <form action="${assignmentDeleteUrl}" method="post">
        <table border="1">
            <caption>Записи на курсы</caption>
            <tr>
                <th>Удалить</th>
                <th>Редактивать</th>
                <th>Курс</th>
                <th>Студент</th>
                <th>Начало обучения</th>
                <th>Конец обучения</th>
            </tr>
            <c:forEach var="assignment" items="${assignmentList}">
                <c:url var="assignmentEditUrl" value="/assignment/edit.html">
                    <c:param name="id" value="${assignment.id}"/>
                </c:url>
                <c:set var="contains" value="${false}"/>
                <c:if test="${tag:contains(freeAssignments, assignment)}">
                    <c:set var="contains" value="${true}"/>
                </c:if>
                <tr>
                    <td><input type="checkbox" ${contains ? "" : "disabled=&quot;&quot;"} name="id"
                               value="${assignment.id}"></td>
                    <td><a href="${assignmentEditUrl}">ред.</a></td>
                    <td>${assignment.course.name}</td>
                    <td>${assignment.student.surname} ${assignment.student.name}</td>
                    <td>${assignment.beginDate}</td>
                    <td>${assignment.endDate}</td>
                </tr>
            </c:forEach>
        </table>
        <button type="submit">Удалить</button>
    </form>
    <c:url var="assignmentEditUrl" value="/assignment/edit.html"/>
    <a href="${assignmentEditUrl}">Добавить новый курс обучения для студента</a>
    <c:url var="assignmentSearchUrl" value="/assignment/search.html"/>
    <form action="${assignmentSearchUrl}">
        Произвести поиск по:
        <p><input type="radio" name="startDate" value="${true}" checked="checked">Дате начала начала обучения<br>
            <input type="radio" name="startDate" value="${false}">Дате окончания обучения<br></p>
        Дата началака/окончания обучения c:
        <input type="date" name="fromDate">
        по:
        <input type="date" name="toDate">
        <button type="submit">Найти</button>
    </form>
    <c:url var="assignmentListUrl" value="/assignment/list.html"/>
    <form action="${assignmentListUrl}">
        <button type="submit">Сбросить результат поиска</button>
    </form>
</tag:head>
<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag"%>
<c:url var="resultSaveUrl" value="/result/save.html"/>
<c:set var="create" value="${empty result}"/>
<c:choose>
    <c:when test="${not create}">
        <c:set var="title"
               value="Редактирование итога по ${result.assignment.course.name} для
                ${result.assignment.student.surname} ${result.assignment.student.name}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Выставление новой оценке"/>
    </c:otherwise>
</c:choose>
<tag:head title="${title}">
<h1>${title}</h1>
<form action="${resultSaveUrl}" method="post">
    <c:if test="${not create}">
        <input type="hidden" name="id" value="${result.id}">
        <input type="hidden" name="assignmentId" value="${result.assignment.id}">
    </c:if>
    <p>Студент и курс:
        <select name="assignmentId" ${create ? ""  : "disabled=&quot;&quot;"}>
            <c:forEach var="assignment" items="${assignmentList}">
                <c:choose>
                    <c:when test="${result.assignment.id == assignment.id}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${assignment.id}" ${selected}>${assignment.student.surname} ${assignment.student.name}
                    записанный на ${assignment.course.name}</option>
            </c:forEach>
        </select>
    <p>Оценка:
        <select name="mark">
            <c:forEach var="i" begin="1" end="10" step="1">
                <c:choose>
                    <c:when test="${i == result.mark}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${i}" ${selected}>${i}</option>
            </c:forEach>
        </select>
    </p>
    <p>Дата выставления оценки:
        <input type="date" name="markDate" value="${result.date}">
    </p>
    <p>Отзыв:
        <input type="text" size="100" maxlength="512" name="review" value="${result.review}">
    </p>
    <button type="submit">Сохранить</button>
</form>
<c:url var="back" value="/result/list.html"/>
<a href="${back}">Назад</a>
</tag:head>

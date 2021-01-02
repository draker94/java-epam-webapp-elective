<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 10.12.2020
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag"%>
<tag:head title="Список преподавателей">
<c:url var="instructorDeleteUrl" value="/instructor/delete.html"/>
<form action="${instructorDeleteUrl}" method="post">
    <table border="1">
        <caption>Список преподавателей</caption>
        <tr>
            <th>Удалить</th>
            <th>Редактивать</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Учёное звание</th>
        </tr>
        <c:forEach var="instructor" items="${instructors}">                <!-- название списка заменить на instructorsList (везде по аналогии) -->
            <c:url var="instructorEditUrl" value="/instructor/edit.html">
                <c:param name="id" value="${instructor.id}"/>
            </c:url>
            <c:set var="contains" value="${false}"/>
            <c:if test="${tag:contains(freeInstructors, instructor)}">
                <c:set var="contains" value="${true}"/>
            </c:if>
            <tr>
                <td><input type="checkbox" ${contains ? "" : "disabled=&quot;&quot;"} name="id"
                           value="${instructor.id}"></td>
                <td><a href="${instructorEditUrl}">ред.</a></td>
                <td>${instructor.surname}</td>
                <td>${instructor.name}</td>
                <td>${instructor.rank.name}</td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit">Удалить</button>
</form>
<c:url var="instructorEditUrl" value="/instructor/edit.html"/>
<a href="${instructorEditUrl}">Добавить преподавателя</a>
    <c:url var="instructorSearchUrl" value="/instructor/search.html"/>
    <form action="${instructorSearchUrl}">
        <p>Найти преподавателя (преподавателей) по фамилии:
            <input type="text" name="surnameForSearch"></p>
        <button type="submit">Найти</button>
    </form>
    <c:url var="instructorListUrl" value="/instructor/list.html"/>
    <form action="${instructorListUrl}">
        <button type="submit">Сбросить результат поиска</button>
    </form>
</tag:head>

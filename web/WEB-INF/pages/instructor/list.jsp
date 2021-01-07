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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="by/training/resources/translate" />

<fmt:message key="instructor.list.title" var="title"/>
<tag:head title="${title}">
<c:url var="instructorDeleteUrl" value="/instructor/delete.html"/>
<form action="${instructorDeleteUrl}" method="post">
    <table border="1">
        <caption>${title}</caption>
        <tr>
            <th><fmt:message key="label.delete"/></th>
            <th><fmt:message key="label.edit"/></th>
            <th><fmt:message key="instructor.list.table.surname"/></th>
            <th><fmt:message key="instructor.list.table.name"/></th>
            <th><fmt:message key="instructor.list.table.rank"/></th>
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
                <td><a href="${instructorEditUrl}"><fmt:message key="label.ed"/></a></td>
                <td>${instructor.surname}</td>
                <td>${instructor.name}</td>
                <td>${instructor.rank.name}</td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit"><fmt:message key="label.delete"/></button>
</form>
<c:url var="instructorEditUrl" value="/instructor/edit.html"/>
<a href="${instructorEditUrl}"><fmt:message key="instructor.list.label.add"/></a>
    <c:url var="instructorSearchUrl" value="/instructor/search.html"/>
    <form action="${instructorSearchUrl}">
        <p><fmt:message key="instructor.list.label.search"/>
            <input type="text" name="surnameForSearch" required></p>
        <button type="submit"><fmt:message key="label.search"/></button>
    </form>
    <c:url var="instructorListUrl" value="/instructor/list.html"/>
    <form action="${instructorListUrl}">
        <button type="submit"><fmt:message key="label.reset"/></button>
    </form>
    <tag:buttons/>
</tag:head>

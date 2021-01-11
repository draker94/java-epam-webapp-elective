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
<form action="<c:url value='/instructor/delete.html'/>" method="post">
    <table border="1">
        <caption>${title}</caption>
        <tr>
            <th><fmt:message key="label.delete"/></th>
            <th><fmt:message key="label.edit"/></th>
            <th><fmt:message key="instructor.list.table.surname"/></th>
            <th><fmt:message key="instructor.list.table.name"/></th>
            <th><fmt:message key="instructor.list.table.rank"/></th>
        </tr>
        <c:forEach var="instructor" items="${instructors}">
            <c:url var="instructorEditUrl" value="/instructor/edit.html">
                <c:param name="id" value="${instructor.id}"/>
            </c:url>
            <c:set var="contains" value="${false}"/>
            <c:if test="${tag:contains(freeInstructors, instructor)}">
                <c:set var="contains" value="${true}"/>
            </c:if>
            <tr>
                <td><label>
                    <input type="checkbox" ${contains ? "" : "disabled=''"} name="id" value="${instructor.id}">
                </label></td>
                <td><a href="${instructorEditUrl}"><fmt:message key="label.ed"/></a></td>
                <td>${instructor.surname}</td>
                <td>${instructor.name}</td>
                <td>${instructor.rank.name}</td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit"><fmt:message key="label.delete"/></button>
</form>
<a href="<c:url value='/instructor/edit.html'/>"><fmt:message key="instructor.list.label.add"/></a>
    <form action="<c:url value='/instructor/search.html'/>">
        <p><fmt:message key="instructor.list.label.search"/>
            <label>
                <input type="text" name="surnameForSearch" required>
            </label></p>
        <button type="submit"><fmt:message key="label.search"/></button>
    </form>
    <form action="<c:url value='/instructor/list.html'/>">
        <button type="submit"><fmt:message key="label.reset"/></button>
    </form>
    <tag:buttons/>
</tag:head>

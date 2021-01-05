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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="by/training/resources/translate"/>

<fmt:message key="assignment.list.title" var="title"/>
<tag:head title="${title}">
    <c:url var="assignmentDeleteUrl" value="/assignment/delete.html"/>
    <form action="${assignmentDeleteUrl}" method="post">
        <table border="1">
            <caption>${title}</caption>
            <tr>
                <th><fmt:message key="label.delete"/></th>
                <th><fmt:message key="label.edit"/></th>
                <th><fmt:message key="assignment.list.table.course"/></th>
                <th><fmt:message key="assignment.list.table.student"/></th>
                <th><fmt:message key="assignment.list.table.start"/></th>
                <th><fmt:message key="assignment.list.table.end"/></th>
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
                    <td><a href="${assignmentEditUrl}"><fmt:message key="label.ed"/></a></td>
                    <td>${assignment.course.name}</td>
                    <td>${assignment.student.surname} ${assignment.student.name}</td>
                    <td>${assignment.beginDate}</td>
                    <td>${assignment.endDate}</td>
                </tr>
            </c:forEach>
        </table>
        <button type="submit"><fmt:message key="label.delete"/></button>
    </form>
    <c:url var="assignmentEditUrl" value="/assignment/edit.html"/>
    <a href="${assignmentEditUrl}"><fmt:message key="assignment.list.label.add"/></a>
    <c:url var="assignmentSearchUrl" value="/assignment/search.html"/>
    <form action="${assignmentSearchUrl}">
        <fmt:message key="assignment.list.label.search"/>
        <p><input type="radio" name="startDate" value="${true}" checked="checked"><fmt:message
                key="assignment.list.label.searchbystart"/><br>
            <input type="radio" name="startDate" value="${false}"><fmt:message key="assignment.list.label.searchbyend"/><br>
        </p>
        <fmt:message key="assignment.list.label.from"/>
        <input type="date" name="fromDate">
        <fmt:message key="assignment.list.label.to"/>
        <input type="date" name="toDate">
        <button type="submit"><fmt:message key="label.search"/></button>
    </form>
    <c:url var="assignmentListUrl" value="/assignment/list.html"/>
    <form action="${assignmentListUrl}">
        <button type="submit"><fmt:message key="label.reset"/></button>
    </form>
</tag:head>
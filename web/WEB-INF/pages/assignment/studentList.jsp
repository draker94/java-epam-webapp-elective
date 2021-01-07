<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 05.01.2021
  Time: 19:44
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

<fmt:message key="assignment.student_list.title" var="title"/>
<tag:head title="${title}">
    <c:url var="assignmentDeleteUrl" value="/assignment/delete.html"/>
    <table border="1">
        <caption>${title}</caption>
        <tr>
            <th><fmt:message key="assignment.list.table.course"/></th>
            <th><fmt:message key="assignment.list.table.student"/></th>
            <th><fmt:message key="assignment.list.table.start"/></th>
            <th><fmt:message key="assignment.list.table.end"/></th>
        </tr>
        <c:forEach var="assignment" items="${assignmentList}">
            <tr>
                <td>${assignment.course.name}</td>
                <td>${assignment.student.surname} ${assignment.student.name}</td>
                <td>${assignment.beginDate}</td>
                <td>${assignment.endDate}</td>
            </tr>
        </c:forEach>
    </table>
    <c:url var="assignmentEnrollUrl" value="/assignment/enroll.html">
        <c:param name="studentId" value="${sessionUser.id}"/>
    </c:url>
    <a href="${assignmentEnrollUrl}"><fmt:message key="assignment.student_list.label.enroll"/></a>
    <tag:buttons/>
</tag:head>

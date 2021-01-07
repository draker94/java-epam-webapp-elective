<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 02.01.2021
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="by/training/resources/translate" />

<fmt:message key="account.title" var="title"/>
<tag:head title="${title}">
    <p><a href="${pageContext.request.contextPath}/course/list.html"><fmt:message key="account.label.list_course"/></a></p>
    <p><a href="${pageContext.request.contextPath}/student/list.html"><fmt:message key="account.label.list_student"/></a></p>
    <p><a href="${pageContext.request.contextPath}/assignment/list.html"><fmt:message key="account.label.list_assignment"/></a></p>
    <p><a href="${pageContext.request.contextPath}/result/list.html"><fmt:message key="account.label.list_result"/></a></p>
    <c:url var="instructorMyCoursesUrl" value="/course/search.html">
        <c:param name="condition" value="${sessionUser.id}"/>
    </c:url>
    <p><a href="${instructorMyCoursesUrl}"><fmt:message key="account.label.instructor.my_course"/></a></p>
    <c:url var="instructorMyAssignmentsUrl" value="/assignment/instructor-list.html">
        <c:param name="instructorId" value="${sessionUser.id}"/>
    </c:url>
    <p><a href="${instructorMyAssignmentsUrl}"><fmt:message key="account.label.instructor.my_assignment"/></a></p>
</tag:head>
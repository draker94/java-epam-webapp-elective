<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="by.training.enums.Roles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="by/training/resources/translate" />

<fmt:message key="course.list.title" var="title"/>
<tag:head title="${title}">
    <c:url var="courseDeleteUrl" value="/course/delete.html"/>
    <form action="${courseDeleteUrl}" method="post">
        <table border="1">
            <caption>${title}</caption>
            <tr>
                <th><fmt:message key="label.delete"/></th>
                <th><fmt:message key="label.edit"/></th>
                <th><fmt:message key="instructor.list.table.name"/></th>
                <th><fmt:message key="instructor.list.table.hours"/></th>
                <th><fmt:message key="instructor.list.table.instructor"/></th>
                <th><fmt:message key="instructor.list.table.descr"/></th>
            </tr>
            <c:forEach var="course" items="${coursesList}">
                <c:url var="courseEditUrl" value="/course/edit.html">
                    <c:param name="id" value="${course.id}"/>
                </c:url>
                <c:set var="contains" value="${false}"/>
                <c:if test="${tag:contains(freeCourses, course)}">
                    <c:set var="contains" value="${true}"/>
                </c:if>
                <tr>
                    <td><input type="checkbox" ${contains ? "" : "disabled=&quot;&quot;"} name="id"
                               value="${course.id}">
                    </td>
                    <td><a href="${courseEditUrl}"><fmt:message key="label.ed"/></a></td>
                    <td>${course.name}</td>
                    <td>${course.hours}</td>
                    <td>${course.instructor.surname} ${course.instructor.name}</td>
                    <td>${course.description}</td>
                </tr>
            </c:forEach>
        </table>
        <button type="submit"><fmt:message key="label.delete"/></button>
    </form>
    <c:url var="courseEditUrl" value="/course/edit.html"/>
    <a href="${courseEditUrl}"><fmt:message key="course.list.label.add"/></a>
    <c:url var="courseSearchUrl" value="/course/search.html"/>
    <form action="${courseSearchUrl}">
        <p><fmt:message key="course.list.label.search"/>
            <input type="text" name="condition"></p>
        <button type="submit"><fmt:message key="label.search"/></button>
    </form>
    <c:url var="courseListUrl" value="/course/list.html"/>
    <form action="${courseListUrl}">
        <button type="submit"><fmt:message key="label.reset"/></button>
    </form>
    <c:if test="${sessionUser.role == Roles.STUDENT}">
        <c:url var="assignmentSaveUrl" value="/assignment/enroll.html">
            <c:param name="studentId" value="${sessionUser.id}"/>
        </c:url>
        <a href="${assignmentSaveUrl}"><fmt:message key="course.list.label.enroll"/></a>
    </c:if>
</tag:head>
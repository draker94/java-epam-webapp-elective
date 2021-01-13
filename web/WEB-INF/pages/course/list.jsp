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
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="by/training/resources/translate"/>

<c:set var="visibility" value="${sessionUser.role == Roles.INSTRUCTOR ? '' : 'hide'}"/>
<fmt:message key="course.list.title" var="title"/>
<tag:head title="${title}">
    <form action="<c:url value='/course/delete.html'/>" method="post">
        <table border="1">
            <caption>${title}</caption>
            <tr>
                <th class="${visibility}"><fmt:message key="label.delete"/></th>
                <th class="${visibility}"><fmt:message key="label.edit"/></th>
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
                    <td class="${visibility}"><label>
                        <input type="checkbox" ${contains ? "" : "disabled=''"} name="id" value="${course.id}">
                    </label>
                    </td>
                    <td class="${visibility}"><a href="${courseEditUrl}"><fmt:message key="label.ed"/></a></td>
                    <td>${course.name}</td>
                    <td>${course.hours}</td>
                    <td>${course.instructor.surname} ${course.instructor.name}</td>
                    <td>${course.description}</td>
                </tr>
            </c:forEach>
        </table>
        <button class="${visibility}" type="submit"><fmt:message key="label.delete"/></button>
    </form>
    <a class="${visibility}" href="<c:url value='/course/edit.html'/>"><fmt:message key="course.list.label.add"/></a>
    <form action="<c:url value="/course/search.html"/>">
        <p><fmt:message key="course.list.label.search"/>
            <label>
                <input type="text" name="condition" required>
            </label></p>
        <button type="submit"><fmt:message key="label.search"/></button>
    </form>
    <form action="<c:url value='/course/list.html'/>">
        <button type="submit"><fmt:message key="label.reset"/></button>
    </form>
    <tag:buttons/>
</tag:head>
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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="by/training/resources/translate" />

<c:choose>
    <c:when test="${not empty course}">
        <fmt:message key="course.edit.label.edit" var="title"/>
    </c:when>
    <c:otherwise>
        <fmt:message key="course.edit.label.create" var="title"/>
    </c:otherwise>
</c:choose>
<tag:head title="${title}">
<form action="<c:url value='/course/save.html'/>" method="post">
    <c:if test="${not empty course}">
        <input type="hidden" name="id" value="${course.id}">
    </c:if>
    <p><fmt:message key="course.edit.label.name"/>
        <label>
            <input type="text" name="name" value="${course.name}" required>
        </label></p>
    <p><fmt:message key="course.edit.label.instructor"/>
        <label>
            <select name="instructorId">
                <c:forEach var="instructor" items="${instructorList}">
                    <c:choose>
                        <c:when test="${sessionUser.id == instructor.id}">
                            <c:set var="selected" value="selected"/>
                        </c:when>
                        <c:otherwise>
                            <c:remove var="selected"/>
                        </c:otherwise>
                    </c:choose>
                    <option value="${instructor.id}" ${selected}>${instructor.name} ${instructor.surname}</option>
                </c:forEach>
            </select>
        </label>
    </p>
    <p><fmt:message key="course.edit.label.hours"/>
        <label>
            <input type="number" max="256" name="hours" value="${course.hours}" required>
        </label></p>
    <p><fmt:message key="course.edit.label.descr"/>
        <label>
            <input type="text" size="100" maxlength="512" name="description" value="${course.description}" required>
        </label></p>
    <button type="submit"><fmt:message key="label.save"/></button>
</form>
    <tag:buttons/>
</tag:head>

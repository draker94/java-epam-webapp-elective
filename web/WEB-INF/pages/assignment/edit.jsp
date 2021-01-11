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

<c:set var="create" value="${empty assignment}"/>
<c:choose>
    <c:when test="${not create}">
        <fmt:message key="assignment.edit.label.edit" var="title"/>
    </c:when>
    <c:otherwise>
        <fmt:message key="assignment.edit.label.add" var="title"/>
    </c:otherwise>
</c:choose>
<tag:head title="${title}">
    <form action="<c:url value='/assignment/save.html'/>" method="post">
        <c:if test="${not create}">
            <input type="hidden" name="id" value="${assignment.id}">
            <input type="hidden" name="studentId" value="${assignment.student.id}">
        </c:if>
        <p><fmt:message key="assignment.edit.label.student"/>
            <label>
                <select name="studentId" ${create ? ""  : "disabled=''"}>
                    <c:forEach var="student" items="${studentList}">
                        <c:choose>
                            <c:when test="${assignment.student.id == student.id}">
                                <c:set var="selected" value="selected"/>
                            </c:when>
                            <c:otherwise>
                                <c:remove var="selected"/>
                            </c:otherwise>
                        </c:choose>
                        <option value="${student.id}" ${selected}>${student.name} ${student.surname}</option>
                    </c:forEach>
                </select>
            </label>
        </p>
        <p><fmt:message key="assignment.edit.label.course"/>
            <label>
                <select name="courseId">
                    <c:forEach var="course" items="${courseList}">
                        <c:choose>
                            <c:when test="${assignment.course.id == course.id}">
                                <c:set var="selected" value="selected"/>
                            </c:when>
                            <c:otherwise>
                                <c:remove var="selected"/>
                            </c:otherwise>
                        </c:choose>
                        <option value="${course.id}" ${selected}>${course.name}</option>
                    </c:forEach>
                </select>
            </label>
        </p>
        <p><fmt:message key="assignment.edit.label.start"/>
            <label>
                <input type="date" name="beginDate" value="${assignment.beginDate}">
            </label>
        </p>
        <p><fmt:message key="assignment.edit.label.end"/>
            <label>
                <input type="date" name="endDate" value="${assignment.endDate}">
            </label>
        </p>
        <button type="submit"><fmt:message key="label.save"/></button>
    </form>
    <tag:buttons/>
</tag:head>

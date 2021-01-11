<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 20:08
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

<c:set var="create" value="${empty result}"/>
<c:set var="createBasedOn" value="${not empty param.assignmentIdForCreate}"/>
<c:choose>
    <c:when test="${not create}">
        <fmt:message key="result.edit.label.edit" var="title"/>
    </c:when>
    <c:otherwise>
        <fmt:message key="result.edit.label.create" var="title"/>
    </c:otherwise>
</c:choose>
<tag:head title="${title}">
    <h1>${title}</h1>
    <form action="<c:url value='/result/save.html'/>" method="post">
        <c:choose>
            <c:when test="${not create}">
                <input type="hidden" name="id" value="${result.id}">
                <input type="hidden" name="assignmentId" value="${result.assignment.id}">
            </c:when>
            <c:when test="${createBasedOn}">
                <input type="hidden" name="assignmentId" value=${param.assignmentIdForCreate}>
            </c:when>
        </c:choose>
        <fmt:message key="result.edit.label.student"/>
        <label>
            <select name="assignmentId" ${create && not createBasedOn ? ""  : "disabled=''"}>
                <c:forEach var="assignment" items="${assignmentList}">
                    <c:choose>
                        <c:when test="${result.assignment.id == assignment.id || param.assignmentIdForCreate == assignment.id}">
                            <c:set var="selected" value="selected"/>
                        </c:when>
                        <c:otherwise>
                            <c:remove var="selected"/>
                        </c:otherwise>
                    </c:choose>
                    <option value="${assignment.id}" ${selected}>${assignment.student.surname} ${assignment.student.name}
                        <fmt:message key="result.edit.label.descr"/> ${assignment.course.name}</option>
                </c:forEach>
            </select>
        </label>
        <p><fmt:message key="result.edit.label.mark"/>
            <label>
                <select name="mark">
                    <c:forEach var="i" begin="1" end="10" step="1">
                        <c:choose>
                            <c:when test="${i == result.mark}">
                                <c:set var="selected" value="selected"/>
                            </c:when>
                            <c:otherwise>
                                <c:remove var="selected"/>
                            </c:otherwise>
                        </c:choose>
                        <option value="${i}" ${selected}>${i}</option>
                    </c:forEach>
                </select>
            </label>
        </p>
        <p><fmt:message key="result.edit.label.date"/>
            <label>
                <input type="date" name="markDate" value="${result.date}" required>
            </label>
        </p>
        <p><fmt:message key="result.edit.label.review"/>
            <label>
                <input type="text" size="100" maxlength="512" name="review" value="${result.review}" required>
            </label>
        </p>
        <button type="submit"><fmt:message key="label.save"/></button>
    </form>
    <tag:buttons/>
</tag:head>

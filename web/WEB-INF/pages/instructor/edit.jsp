<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 14.12.2020
  Time: 20:23
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

<c:set var="canSave" value="${true}"/>
<c:url var="instructorSaveUrl" value="/instructor/save.html">
    <c:param name="isNewInstructor" value="${(not empty freeUsersList) ? true : false}"/>
</c:url>
<c:choose>
    <c:when test="${not empty instructor}">
        <fmt:message key="instructor.edit.label.edit" var="title"/>
    </c:when>
    <c:otherwise>
        <fmt:message key="instructor.edit.label.create" var="title"/>
    </c:otherwise>
</c:choose>
<tag:head title="${title}">
    <form action="${instructorSaveUrl}" method="post">
        <c:if test="${not empty instructor}">
            <input type="hidden" name="id" value="${instructor.id}">
        </c:if>
        <p><fmt:message key="instructor.edit.label.name"/>
            <label>
                <input type="text" name="name" value="${instructor.name}" required>
            </label></p>
        <p><fmt:message key="instructor.edit.label.surname"/>
            <label>
                <input type="text" name="surname" value="${instructor.surname}" required>
            </label></p>
        <p><fmt:message key="instructor.edit.label.rank"/>
            <label>
                <select name="rank">
                    <c:forEach var="rank" items="${ranks}">
                        <c:choose>
                            <c:when test="${rank == instructor.rank}">
                                <c:set var="selected" value="selected"/>
                            </c:when>
                            <c:otherwise>
                                <c:remove var="selected"/>
                            </c:otherwise>
                        </c:choose>
                        <option value="${rank}" ${selected}>${rank.name}</option>
                    </c:forEach>
                </select>
            </label>
        </p>
        <c:if test="${empty instructor}">
            <c:choose>
                <c:when test="${empty freeUsersList}">
                    <p><fmt:message key="label.not_free_users"/></p>
                    <c:set var="canSave" value="${false}"/>
                </c:when>
                <c:otherwise>
                    <label>
                        <select name="id">
                            <c:forEach var="user" items="${freeUsersList}">
                                <option value="${user.id}">${user.login} [${user.role.name}]</option>
                            </c:forEach>
                        </select>
                    </label>
                </c:otherwise>
            </c:choose>
        </c:if>
        <button ${canSave ? ""  : "disabled=&quot;&quot;"} type="submit"><fmt:message key="label.save"/></button>
    </form>
    <tag:buttons/>
</tag:head>

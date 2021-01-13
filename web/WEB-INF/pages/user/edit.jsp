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

<c:choose>
    <c:when test="${not empty user}">
        <fmt:message key="user.edit.label.edit" var="title"/>
    </c:when>
    <c:otherwise>
        <fmt:message key="user.edit.label.create" var="title"/>
    </c:otherwise>
</c:choose>
<tag:head title="${title}">
    <form action="<c:url value='/user/save.html'/>" method="post">
        <c:if test="${not empty user}">
            <input type="hidden" name="id" value="${user.id}">
        </c:if>
        <p><fmt:message key="user.edit.label.name"/>
            <label>
                <input type="text" name="login" value="${user.login}" required>
            </label></p>
        <p><fmt:message key="user.edit.label.pass"/>
            <label>
                <input type="text" name="password" value="${user.password}" required>
            </label></p>
        <p><fmt:message key="user.edit.label.role"/>
            <label>
                <select name="role">
                    <c:forEach var="role" items="${roles}">
                        <c:choose>
                            <c:when test="${role == user.role}">
                                <c:set var="selected" value="selected"/>
                            </c:when>
                            <c:otherwise>
                                <c:remove var="selected"/>
                            </c:otherwise>
                        </c:choose>
                        <option value="${role}"${selected}>${role.name}</option>
                    </c:forEach>
                </select>
            </label>
        </p>
        <button type="submit"><fmt:message key="label.save"/></button>
    </form>
    <tag:buttons/>
</tag:head>

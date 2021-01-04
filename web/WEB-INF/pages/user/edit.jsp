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
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="by/training/resources/translate" />

<c:choose>
    <c:when test="${not empty user}">
        <fmt:message key="user.edit.label.edit" var="title"/>
    </c:when>
    <c:otherwise>
        <fmt:message key="user.edit.label.create" var="title"/>
    </c:otherwise>
</c:choose>
<tag:head title="${title}">
    <c:url var="userSaveUrl" value="/user/save.html"/>
    <form action="${userSaveUrl}" method="post">
        <c:if test="${not empty user}">
            <input type="hidden" name="id" value="${user.id}">
        </c:if>
        <p><fmt:message key="user.edit.label.name"/>
            <input type="text" name="login" value="${user.login}"></p>
        <p><fmt:message key="user.edit.label.pass"/>
            <input type="text" name="password" value="${user.password}"></p>
        <p><fmt:message key="user.edit.label.mail"/>
            <input type="text" name="mail" value="${user.mail}"></p>
        <p><fmt:message key="user.edit.label.role"/>
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
        </p>
        <button type="submit"><fmt:message key="label.save"/></button>
    </form>
    <c:url var="back" value="/assignment/list.html"/>
    <a href="${back}"><fmt:message key="label.back"/></a>
</tag:head>

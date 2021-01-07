<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 24.12.2020
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="by/training/resources/translate" />

<c:if test="${not empty param.message}">
    <p>${param.message}</p>
</c:if>
<fmt:message key="main.login.title" var="title"/>
<tag:head title="${title}">
<h1>${title}</h1>
<c:url var="loginUrl" value="/main/login.html"/>
<form action="${loginUrl}" method="post">
    <p><fmt:message key="main.login.label.username"/>
        <input type="text" name="login" required>
    </p>
    <p><fmt:message key="main.login.label.pass"/>
        <input type="password" name="password" required>
    </p>
    <button type="submit"><fmt:message key="main.login.label.enter"/></button>
</form>
</tag:head>
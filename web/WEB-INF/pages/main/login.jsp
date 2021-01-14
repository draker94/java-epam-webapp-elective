<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 24.12.2020
  Time: 23:02
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

<tag:locale/>
<div class="title"><fmt:message key="main.login.title" var="title"/>
<tag:head title="${title}">
    <c:if test="${not empty error}">
        <p class="notification"><fmt:message key="${error}"/></p>
    </c:if>
    <h1>${title}</h1>
    <form action="<c:url value='/main/login.html'/>" method="post">
            <p><fmt:message key="main.login.label.username"/>
                <label>
                    <input type="text" name="login" required>
                </label></p>
            <p><fmt:message key="main.login.label.pass"/>
                <label>
                    <input type="password" name="password" required>
                </label></p>
            <p>
                <button type="submit"><fmt:message key="main.login.label.enter"/></button>
            </p>
    </form>
    </div>
</tag:head>
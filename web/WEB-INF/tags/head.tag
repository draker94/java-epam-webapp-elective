<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="by/training/resources/translate"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <c:url var="urlCss" value="/styles/common.css"/>
    <link href="${urlCss}" rel="stylesheet">
</head>
<body>
<div align="center">
    <h1><fmt:message key="application.title"/></h1>
    <c:if test="${not empty sessionUser}">
        <fmt:message key="application.welcome"/> ${sessionUser.login}<br/>
        <fmt:message key="application.access"/> ${sessionUser.role.name}<br/>
        <a href="<c:url value='/account/edit.html'/>"><fmt:message key="application.logged"/></a>
        <p><a href="<c:url value='/main/logout.html'/>"><fmt:message key="application.exit"/></a></p>
        <hr>
    </c:if>
    <jsp:doBody/>
</div>
</body>
</html>
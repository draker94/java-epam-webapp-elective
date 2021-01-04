<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="by/training/resources/translate" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <%-- <c:url var="urlCss" value="/main.css"/> --%>
    <%--<link href="${urlCss}" rel="stylesheet"> --%>
</head>
<body>
<form>
    <select name="language" onchange="submit()">
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
    </select>
</form>
<div align="center">
    <h1><fmt:message key="application.title"/></h1>
    <c:if test="${not empty sessionUser}">
        <c:url var="urlLogout" value="/main/logout.html"/>
        <c:url var="urlAccount" value="/account/edit.html"/>
        <fmt:message key="application.welcome"/> ${sessionUser.login}<br/> <!-- Везде переносы вместо p поставить -->
        <fmt:message key="application.access"/> ${sessionUser.role.name}<br/>
        <a href="${urlAccount}"><fmt:message key="application.logged"/></a>
        <p><a href="${urlLogout}"><fmt:message key="application.exit"/></a></p>
        <hr>
    </c:if>
<jsp:doBody/>
</div>
</body>
</html>
<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="by/training/resources/translate"/>

<form>
    <input type="button" value=<fmt:message key="label.back"/> onclick="history.back()">
</form>
<form>
    <input type="button" value=<fmt:message key="label.main"/> onclick="location.href='<c:url value="/index.html"/>'">  <!-- везде сделать так -->
</form>


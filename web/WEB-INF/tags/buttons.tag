<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="by/training/resources/translate"/>


<p><button onclick="history.back()"><fmt:message key="label.back"/></button></p>
<p><button onclick="location.href='<c:url value="/index.html"/>'"><fmt:message key="label.main"/></button></p> <!-- везде сделать так -->



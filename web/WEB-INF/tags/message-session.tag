<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="message" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="by/training/resources/translate"/>

<c:if test="${not empty message}">
    <p class="notification"><fmt:message key="${message}"/></p>
    <c:remove var="message" scope="session" />
</c:if>




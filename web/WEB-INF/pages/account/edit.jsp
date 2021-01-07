<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 29.12.2020
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="by/training/resources/translate" />

<fmt:message key="account.edit.title" var="title"/>
<tag:head title="${title}">
    <c:url var="changeUserUrl" value="/account/update.html"/>
    <c:if test="${not empty param.message}">
        <p>${param.message}</p>
    </c:if>
    <hr>
    <fmt:message key="account.edit.label.change_pass"/>
    <form action="${changeUserUrl}" method="post">
        <p><fmt:message key="account.edit.label.old_pass"/>
            <input type="password" name="oldPassword" required></p>
        <p><fmt:message key="account.edit.label.new_pass"/>
            <input type="password" name="newPassword" required></p>
        <p><fmt:message key="account.edit.label.new_pass_conf"/>
            <input type="password" name="newPasswordConfirm" required></p>
        <button type="submit"><fmt:message key="account.edit.label.apply"/></button>
        <hr>
    </form>
    <form action="${changeUserUrl}" method="post">
        <fmt:message key="account.edit.label.change_mail"/>
        <p><input type="text" name="newMail" value="${sessionUser.mail}" required></p>
        <button type="submit"><fmt:message key="account.edit.label.apply"/></button>
    </form>
    <tag:buttons/>
</tag:head>

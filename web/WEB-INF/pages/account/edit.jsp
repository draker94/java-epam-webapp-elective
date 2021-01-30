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
    <p><b>${fullName}</b></p>
    <c:url var="changeUserUrl" value="/account/update.html"/>
    <c:if test="${not empty message}">
        <p class="notification"><fmt:message key="${message}"/></p>
    </c:if>
    <fmt:message key="account.edit.label.change_pass"/>
    <form action="${changeUserUrl}" method="post">
        <p><fmt:message key="account.edit.label.old_pass"/>
            <label>
                <input type="password" name="oldPassword" required>
            </label></p>
        <p><fmt:message key="account.edit.label.new_pass"/>
            <label>
                <input type="password" name="newPassword" required>
            </label></p>
        <p><fmt:message key="account.edit.label.new_pass_conf"/>
            <label>
                <input type="password" name="newPasswordConfirm" required>
            </label></p>
        <button type="submit"><fmt:message key="account.edit.label.apply"/></button>
        <hr>
    </form>
    <form action="${changeUserUrl}" method="post">
        <fmt:message key="account.edit.label.change_mail"/>
        <p><label>
            <input type="text" name="newMail" value="${sessionUser.mail}" required>
        </label></p>
        <button type="submit"><fmt:message key="account.edit.label.apply"/></button>
    </form>
    <tag:buttons/>
</tag:head>

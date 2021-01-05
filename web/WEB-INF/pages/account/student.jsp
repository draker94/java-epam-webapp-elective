<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 02.01.2021
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="by/training/resources/translate" />

<fmt:message key="account.title" var="title"/>
<tag:head title="${title}">
    <p><a href="${pageContext.request.contextPath}/course/list.html"><fmt:message key="account.label.listcourse"/></a></p>
    <p><a href="${pageContext.request.contextPath}/assignment/student-list.html"><fmt:message key="account.label.liststudentassignment"/></a></p>
    <p><a href="${pageContext.request.contextPath}/result/list.html"><fmt:message key="account.label.listresult"/></a></p>
</tag:head>
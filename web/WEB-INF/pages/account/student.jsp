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

<tag:locale/>
<div class="title"><fmt:message key="account.title" var="title"/>
<tag:head title="${title}">
    <p><a href="<c:url value='/assignment/student-list.html'/>"><fmt:message key="account.label.student.my_assignment"/></a></p>
    <p><a href="<c:url value='/result/student-list.html'/>"><fmt:message key="account.label.list_result_student"/></a></p>
    <hr>
    <p><a href="<c:url value='/course/list.html'/>"><fmt:message key="account.label.list_course"/></a></p>
</tag:head></div>
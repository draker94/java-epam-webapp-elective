<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 10.12.2020
  Time: 19:29
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

<fmt:message key="user.list.title" var="title"/>
<tag:head title="${title}">
    <c:url var="userDeleteUrl" value="/user/delete.html"/>
    <form action="${userDeleteUrl}" method="post">
        <table border="1">
            <caption>${title}</caption>
            <tr>
                <th><fmt:message key="label.delete"/></th>
                <th><fmt:message key="label.edit"/></th>
                <th><fmt:message key="label.id"/></th>
                <th><fmt:message key="user.list.table.login"/></th>
                <th><fmt:message key="user.list.table.pass"/></th>
                <th><fmt:message key="user.list.table.mail"/></th>
                <th><fmt:message key="user.list.table.role"/></th>
            </tr>
            <c:forEach var="user" items="${users}">
            <c:url var="userEditUrl" value="/user/edit.html">
                <c:param name="id" value="${user.id}"/>
            </c:url>
            <c:set var="contains" value="${false}"/>
            <c:if test="${tag:contains(freeUsers, user)}">
                <c:set var="contains" value="${true}"/>
            </c:if>
            <tr>
                <td><label>
                    <input type="checkbox" ${contains ? "" : "disabled=&quot;&quot;"} name="id" value="${user.id}">
                </label></td>
                <td><a href="${userEditUrl}"><fmt:message key="label.ed"/></a></td>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.mail}</td>
                <td>${user.role.name}</td>
                </c:forEach>
            </tr>
        </table>
        <button type="submit"><fmt:message key="label.delete"/></button>
    </form>
    <c:url var="userEditUrl" value="/user/edit.html"/>
    <a href="${userEditUrl}"><fmt:message key="user.list.label.add"/></a>
    <tag:buttons/>
</tag:head>
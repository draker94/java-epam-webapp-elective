<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="by.training.enums.Roles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="by/training/resources/translate" />

<c:set var="visibility" value="${sessionUser.role == Roles.INSTRUCTOR ? '' : 'hide'}"/>
<fmt:message key="result.list.title" var="title"/>
<tag:head title="${title}">
    <tag:message-session message="${message}"/>
    <form action="<c:url value='/result/delete.html'/>" method="post">
        <table border="1">
            <caption>${title}</caption>
            <tr>
                <th class="${visibility}"><fmt:message key="label.delete"/></th>
                <th class="${visibility}"><fmt:message key="label.edit"/></th>
                <th><fmt:message key="result.list.table.name"/></th>
                <th><fmt:message key="result.list.table.student"/></th>
                <th><fmt:message key="result.list.table.mark"/></th>
                <th><fmt:message key="result.list.table.date"/></th>
                <th><fmt:message key="result.list.table.review"/></th>
            </tr>
            <c:forEach var="result" items="${resultList}">
                <c:url var="resultEditUrl" value="/result/edit.html">
                    <c:param name="id" value="${result.id}"/>
                </c:url>
                <tr>
                    <td class="${visibility}"><label>
                        <input type="checkbox" name="id" value="${result.id}">
                    </label></td>
                    <td class="${visibility}"><a href="${resultEditUrl}"><fmt:message key="label.ed"/></a></td>
                    <td>${result.assignment.course.name}</td>
                    <td>${result.assignment.student.surname} ${result.assignment.student.name}</td>
                    <td>${result.mark}</td>
                    <td>${result.date}</td>
                    <td>${result.review}</td>
                </tr>
            </c:forEach>
        </table>
        <button class="${visibility}" type="submit"><fmt:message key="label.delete"/></button>
    </form>
    <a class="${visibility}" href="<c:url value='/result/edit.html'/>"><fmt:message key="result.list.table.new_mark"/></a>
    <form class="${visibility}" action="<c:url value='/result/search.html'/>">
        <p><fmt:message key="result.list.table.find_from"/>
            <label>
                <select name="from">
                    <c:forEach var="i" begin="0" end="10">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </label>
                <fmt:message key="result.list.table.find_to"/>
            <label>
                <select name="to">
                    <c:forEach var="i" begin="0" end="10">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </label>
            <button type="submit"><fmt:message key="label.search"/></button>
    </form>
    <form class="${visibility}" action="<c:url value='/result/list.html'/>">
        <button type="submit"><fmt:message key="label.reset"/></button>
    </form>
    <tag:buttons/>
</tag:head>
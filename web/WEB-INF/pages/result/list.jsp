<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="by/training/resources/translate" />

<fmt:message key="result.list.title" var="title"/>
<tag:head title="${title}">
    <c:url var="resultDeleteUrl" value="/result/delete.html"/>
    <form action="${resultDeleteUrl}" method="post">
        <table border="1">
            <caption>${title}</caption>
            <tr>
                <th><fmt:message key="label.delete"/></th>
                <th><fmt:message key="label.edit"/></th>
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
                    <td><input type="checkbox" name="id" value="${result.id}"></td>
                    <td><a href="${resultEditUrl}"><fmt:message key="label.ed"/></a></td>
                    <td>${result.assignment.course.name}</td>
                    <td>${result.assignment.student.name}</td>
                    <td>${result.mark}</td>
                    <td>${result.date}</td>
                    <td>${result.review}</td>
                </tr>
            </c:forEach>
        </table>
        <button type="submit"><fmt:message key="label.delete"/></button>
    </form>
    <c:url var="resultEditUrl" value="/result/edit.html"/>
    <a href="${resultEditUrl}"><fmt:message key="result.list.table.newmark"/></a>
    <c:url var="resultSearchUrl" value="/result/search.html"/>
    <form action="${resultSearchUrl}">
        <p><fmt:message key="result.list.table.findfrom"/>
            <select name="from">
                <c:forEach var="i" begin="0" end="10">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
            <fmt:message key="result.list.table.findto"/>
            <select name="to">
                <c:forEach var="i" begin="0" end="10">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
            <button type="submit"><fmt:message key="label.search"/></button>
    </form>
    <c:url var="resultListUrl" value="/result/list.html"/>
    <form action="${resultListUrl}">
        <button type="submit"><fmt:message key="label.reset"/></button>
    </form>
</tag:head>
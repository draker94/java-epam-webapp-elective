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
    <c:url var="resultDeleteUrl" value="/result/delete.html"/>
    <form action="${resultDeleteUrl}" method="post">
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
                    <td class="${visibility}"><input type="checkbox" name="id" value="${result.id}"></td>
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
    <c:url var="resultEditUrl" value="/result/edit.html"/>
    <a class="${visibility}" href="${resultEditUrl}"><fmt:message key="result.list.table.new_mark"/></a>
    <c:url var="resultSearchUrl" value="/result/search.html"/>
    <form class="${visibility}" action="${resultSearchUrl}">
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
    <c:url var="resultListUrl" value="/result/list.html"/>
    <form class="${visibility}" action="${resultListUrl}">
        <button type="submit"><fmt:message key="label.reset"/></button>
    </form>
    <tag:buttons/>
</tag:head>
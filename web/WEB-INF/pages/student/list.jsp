<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="by/training/resources/translate" />

<fmt:message key="student.list.title" var="title"/>
<tag:head title="${title}">
    <c:url var="studentDeleteUrl" value="/student/delete.html"/>
    <form action="${studentDeleteUrl}" method="post">
        <table border="1">
            <caption>${title}</caption>
            <tr>
                <th><fmt:message key="label.delete"/></th>
                <th><fmt:message key="label.edit"/></th>
                <th><fmt:message key="label.id"/></th>
                <th><fmt:message key="student.list.table.surname"/></th>
                <th><fmt:message key="student.list.table.name"/></th>
                <th><fmt:message key="student.list.table.year"/></th>
            </tr>
            <c:forEach var="student" items="${students}">
                <c:url var="studentEditUrl" value="/student/edit.html">
                    <c:param name="id" value="${student.id}"/>
                </c:url>
                <c:set var="contains" value="${false}"/>
                <c:if test="${tag:contains(freeStudents, student)}">
                    <c:set var="contains" value="${true}"/>
                </c:if>
                <tr>
                    <td><label>
                        <input type="checkbox" ${contains ? "" : "disabled=&quot;&quot;"} name="id"
                                   value="${student.id}">
                    </label></td>
                    <td><a href="${studentEditUrl}">ред.</a></td>
                    <td>${student.id}</td>
                    <td>${student.surname}</td>
                    <td>${student.name}</td>
                    <td>${student.studyYear}</td>
                </tr>
            </c:forEach>
        </table>
        <button type="submit"><fmt:message key="label.delete"/></button>
    </form>
    <c:url var="studentEditUrl" value="/student/edit.html"/>
    <a href="${studentEditUrl}"><fmt:message key="student.list.label.add"/></a>
    <c:url var="studentSearchUrl" value="/student/search.html"/>
    <form action="${studentSearchUrl}">
        <p><fmt:message key="student.list.label.search"/>
            <label>
                <input type="text" name="surnameForSearch" required>
            </label></p>
        <button type="submit"><fmt:message key="label.search"/></button>
    </form>
    <c:url var="studentListUrl" value="/student/list.html"/>
    <form action="${studentListUrl}">
        <button type="submit"><fmt:message key="label.reset"/></button>
    </form>
    <tag:buttons/>
</tag:head>
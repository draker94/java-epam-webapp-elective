<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 16:42
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

<c:set var="canSave" value="${true}"/>
<c:url var="studentSaveUrl" value="/student/save.html">
    <c:param name="isNewStudent" value="${(not empty freeUsersList) ? true : false}"/>
</c:url>
<c:choose>
    <c:when test="${not empty student}">
        <fmt:message key="student.edit.label.edit" var="title"/>
    </c:when>
    <c:otherwise>
        <fmt:message key="student.edit.label.create" var="title"/>
    </c:otherwise>
</c:choose>
<tag:head title="${title}">
    <form action="${studentSaveUrl}" method="post">
        <c:if test="${not empty student}">
            <input type="hidden" name="id" value="${student.id}">
        </c:if>
        <p><fmt:message key="student.edit.label.name"/>
            <label>
                <input type="text" name="name" value="${student.name}" required>
            </label></p>
        <p><fmt:message key="student.edit.label.surname"/>
            <label>
                <input type="text" name="surname" value="${student.surname}" required>
            </label></p>
        <p><fmt:message key="student.edit.label.course"/>
            <label>
                <select name="studyYear">          <!--ОПТИМИЗИРОВАТЬ-->
                    <c:forEach var="i" begin="1" end="7" step="1">
                        <c:choose>
                            <c:when test="${i == student.studyYear}">
                                <c:set var="selected" value="selected"/>
                            </c:when>
                            <c:otherwise>
                                <c:remove var="selected"/>
                            </c:otherwise>
                        </c:choose>
                        <option value="${i}" ${selected}>${i}</option>
                    </c:forEach>
                </select>
            </label>
        </p>
        <c:if test="${empty student}">
            <c:choose>
                <c:when test="${empty freeUsersList}">
                    <p><fmt:message key="label.not_free_users"/></p>
                    <c:set var="canSave" value="${false}"/>
                </c:when>
                <c:otherwise>
                    <label>
                        <select name="id">
                            <c:forEach var="user" items="${freeUsersList}">
                                <option value="${user.id}">${user.id} ${user.login} [${user.role}]</option>
                            </c:forEach>
                        </select>
                    </label>
                </c:otherwise>
            </c:choose>
        </c:if>
        <button ${canSave ? ""  : "disabled=&quot;&quot;"} type="submit"><fmt:message key="label.save"/></button>
    </form>
    <tag:buttons/>
</tag:head>

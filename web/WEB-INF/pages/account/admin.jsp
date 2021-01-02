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
<tag:head title="Главная страница">
    <p><a href="${pageContext.request.contextPath}/user/list.html">Список пользователей</a></p>
    <p><a href="${pageContext.request.contextPath}/instructor/list.html">Список преподавателей</a></p>
</tag:head>
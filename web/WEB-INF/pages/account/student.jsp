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
    <p><a href="${pageContext.request.contextPath}/course/list.html">Список курсов</a></p>
    <p><a href="${pageContext.request.contextPath}/assignment/list.html">Список записей на курсы</a></p>
    <p><a href="${pageContext.request.contextPath}/result/list.html">Список итогов</a></p>
</tag:head>
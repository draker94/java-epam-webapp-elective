<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <%-- <c:url var="urlCss" value="/main.css"/> --%>
    <%--<link href="${urlCss}" rel="stylesheet"> --%>
</head>
<body>
<div align="center">
    <h1>Система "Факультет"</h1>
    <c:if test="${not empty sessionUser}">
        <c:url var="urlLogout" value="/main/logout.html"/>
        <c:url var="urlAccount" value="/account/edit.html"/>
        Добро пожаловать - ${sessionUser.login}<br/> <!-- Везде переносы вместо p поставить -->
        Ваш доступ - ${sessionUser.role.name}<br/>
        <a href="${urlAccount}">Личный кабинет</a>
        <p><a href="${urlLogout}">Выход</a></p>
        <hr>
    </c:if>
</div">
<jsp:doBody/>
</body>
</html>
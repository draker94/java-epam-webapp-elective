<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 29.12.2020
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag" %>
<tag:head title="Личный кабинет">
    <c:url var="changeUserUrl" value="/account/update.html"/>
    <c:if test="${not empty param.message}">
        <p>${param.message}</p>
    </c:if>
    <hr>
    Сменить пароля
    <form action="${changeUserUrl}" method="post">
        <p>Старый пароль:
            <input type="password" name="oldPassword"></p>
        <p>Новый пароль:
            <input type="password" name="newPassword"></p>
        <p>Новый пароль (повторить):
            <input type="password" name="newPasswordConfirm"></p>
        <button type="submit">Применить</button>
        <hr>
    </form>
    <form action="${changeUserUrl}" method="post">
        Сменить адрес электронной почты
        <p><input type="text" name="newMail" value="${sessionUser.mail}"></p>
        <button type="submit">Применить</button>
    </form>
</tag:head>

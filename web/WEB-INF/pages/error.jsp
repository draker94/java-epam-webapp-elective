<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 25.12.2020
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Упс! Возникла ошибка!</h1>

<c:out value="${error}" />
<a href="${url}">На главную</a>

</body>
</html>

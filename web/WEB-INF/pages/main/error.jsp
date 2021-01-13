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
    <title>Oooops...</title>
</head>
<body>
<h1>Error!</h1>
<table border="1">
    <tr valign="top">
        <td width="40%"><b>Error:</b></td>
        <td>${error}</td>
    </tr>
    <tr valign="top">
        <td><b>URI:</b></td>
        <td>${uri}</td>
    </tr>
    <tr valign="top">
        <td><b>Stack trace:</b></td>
        <td>
            <c:forEach var="trace" items="${stackTrace}">
                <p>${trace}</p>
            </c:forEach>
        </td>
    </tr>
</table>
</body>
</html>

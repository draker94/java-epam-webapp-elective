<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tag/contains-function.tld" prefix="fn" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Результаты прохождения курсов</title>
</head>
<body>
<c:url var="resultDeleteUrl" value="/result/delete.html"/>
<form action="${resultDeleteUrl}" method="post">
    <table border="1">
        <caption>Результаты прохождения курсов</caption>
        <tr>
            <th>Удалить</th>
            <th>Редактивать</th>
            <th>Курс</th>
            <th>Студент</th>
            <th>Оценка</th>
            <th>Дата выставления</th>
            <th>Отзыв</th>
        </tr>
        <c:forEach var="result" items="${resultList}">
            <c:url var="resultEditUrl" value="/result/edit.html">
                <c:param name="id" value="${result.id}"/>
            </c:url>
            <tr>
                <td><input type="checkbox" name="id" value="${result.id}"></td>
                <td><a href="${resultEditUrl}">ред.</a></td>
                <td>${result.assignment.course.name}</td>
                <td>${result.assignment.student.name}</td>
                <td>${result.mark}</td>
                <td>${result.date}</td>
                <td>${result.review}</td>
            </tr>
        </c:forEach>
    </table>
    <button type="submit">Удалить</button>
</form>
<c:url var="resultEditUrl" value="/result/edit.html"/>
<a href="${resultEditUrl}">Выставить новую отметку</a>
</body>
</html>
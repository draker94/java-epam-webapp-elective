<%--
  Created by IntelliJ IDEA.
  User: Dreykus fon Magnus
  Date: 22.12.2020
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/implicit.tld" prefix="tag" %>
<tag:head title="Результаты прохождения курсов">
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
    <c:url var="resultSearchUrl" value="/result/search.html"/>
    <form action="${resultSearchUrl}">
        <p>Найти результаты с оценками от:
            <select name="from">
                <c:forEach var="i" begin="0" end="10">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
            по (включительно):
            <select name="to">
                <c:forEach var="i" begin="0" end="10">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
            <button type="submit">Найти</button>
    </form>
    <c:url var="resultListUrl" value="/result/list.html"/>
    <form action="${resultListUrl}">
        <button type="submit">Сбросить результат поиска</button>
    </form>
</tag:head>
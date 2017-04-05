<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: artes
  Date: 31.03.17
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meal List</title>
</head>
<body>

<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table>
    <tr>
        <td>Время</td>
        <td>Название</td>
        <td>Калории</td>
        <td>Превышение</td>
    </tr>
<c:forEach items="${meals}" var="meal">
    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>


    <tr bgcolor="${meal.exceed ? "coral" : "white"}">
        <td><%= TimeUtil.dateToString(meal.getDateTime())%></td>
        <td><%= meal.getDescription()%></td>
        <td>${meal.calories}</td>
        <td>${meal.exceed}</td>
        <td>${meal.id}</td>
    </tr>
</c:forEach>
    </table>

</body>
</html>

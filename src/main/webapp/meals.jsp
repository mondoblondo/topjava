<%--
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

<c:forEach var="meal" items="${}">

    ${meal}<br>
</c:forEach>

<h3>Здесь</h3>
<h3>будет</h3>
<h3>что-то полезное</h3>

</body>
</html>

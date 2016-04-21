<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21.04.2016
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Ошибка</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>
<h3>Ошибка!</h3>
<%
    if (exception instanceof ExceptionInInitializerError || exception instanceof NoClassDefFoundError) {
%>
<div>
    Не удалось подклчиться к базе данных!
</div>
<%
    } else {
%>
<div><%=exception.getClass().getSimpleName()%></div>
<div><%=exception.getLocalizedMessage()%></div>
<%
    }
%>

<br><br>
<a href="/">Вернуться в меню</a>
</body>
</html>

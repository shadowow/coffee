<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15.04.2016
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="/views/errorPage.jsp"%>
<html>
<head>
    <title>Заказ оформлен</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>
<h2>Заказ оформлен!</h2>
Ожидайте звонка на номер <%=request.getParameter("phone")%>

<br><br>
<a href="/">Вернуться в меню</a>
</body>
</html>

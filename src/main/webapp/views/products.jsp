<%@ page import="com.coffee.dao.ProductDAO" %>
<%@ page import="com.coffee.logic.Bakery" %>
<%@ page import="com.coffee.logic.Desert" %>
<%@ page import="com.coffee.logic.Drink" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.04.2016
  Time: 1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="/views/errorPage.jsp"%>
<html>
<head>
    <title>Редактор меню</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>
<%
    session.removeAttribute("id");
    ProductDAO productDAO = new ProductDAO();
    // Удаление продукта
    Integer id;
    try {
        if (request.getParameter("bakery_delete") != null) {
            id = Integer.parseInt(request.getParameter("bakery_select"));
            productDAO.delete(id);
        } else if (request.getParameter("desert_delete") != null) {
            id = Integer.parseInt(request.getParameter("desert_select"));
            productDAO.delete(id);
        } else if (request.getParameter("drink_delete") != null) {
            id = Integer.parseInt(request.getParameter("drink_select"));
            productDAO.delete(id);
        }
    } catch (NumberFormatException e) {

    }
%>
<form action="/add_product" method="get">
    <h3>Добавить новый продукт:</h3>
    <select name="product_add">
        <option>Выпечка</option>
        <option>Десерты</option>
        <option>Напитки</option>
    </select>
    <input type="submit" name="add" value="Добавить">
    <br><br>
</form>
<form action="/add_product" method="get">
    <h3>Редактировать продукт:</h3>
    <select name="bakery_select">
        <%
            List<Bakery> bakeryList = productDAO.findAllBakery();
            for (Bakery bakery : bakeryList) {
        %>
        <option value="<%=bakery.getID()%>"><%=bakery.getName()%></option>
        <% } %>
    </select>
    <input type="submit" name="edit" value="Редактировать изделие">
    <br>

    <select name="desert_select">
        <%
            List<Desert> deserts = productDAO.findAllDeserts();
            for (Desert desert : deserts) {
        %>
        <option value="<%=desert.getID()%>"><%=desert.getName()%></option>
        <% } %>
    </select>
    <input type="submit" name="edit" value="Редактировать десерт">
    <br>

    <select name="drink_select">
        <%
            List<Drink> drinks = productDAO.findAllDrinks();
            for (Drink drink : drinks) {
        %>
        <option value="<%=drink.getID()%>"><%=drink.getName()+" "+ drink.getVolume()%></option>
        <% } %>
    </select>
    <input type="submit" name="edit" value="Редактировать напиток">
</form>
<br><br>

<form action="/products" method="post">

    <select name="bakery_select">
        <%
            for (Bakery bakery : bakeryList) {
        %>
        <option value="<%=bakery.getID()%>"><%=bakery.getName()%></option>
        <% } %>
    </select>
    <input type="submit" name="bakery_delete" value="Удалить изделие">
    <br>

    <select name="desert_select">
        <%
            for (Desert desert : deserts) {
        %>
        <option value="<%=desert.getID()%>"><%=desert.getName()%></option>
        <% } %>
    </select>
    <input type="submit" name="desert_delete" value="Удалить десерт">
    <br>

    <select name="drink_select">
        <%
            for (Drink drink : drinks) {
        %>
        <option value="<%=drink.getID()%>"><%=drink.getName()+" "+ drink.getVolume()%></option>
        <% } %>
    </select>
    <input type="submit" name="drink_delete" value="Удалить напиток">
</form>

<br><br>
<a href="/">Вернуться в меню</a>
</body>
</html>

<%@ page import="com.coffee.dao.ProductDAO" %>
<%@ page import="com.coffee.logic.Bakery" %>
<%@ page import="com.coffee.logic.Desert" %>
<%@ page import="com.coffee.logic.Drink" %>
<%@ page import="com.coffee.logic.Product" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDateTime" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.04.2016
  Time: 2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление продукта</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>
<%
    if (request.getParameter("save") != null) {
        Product product = new Product();
        product.setName(request.getParameter("name"));
        product.setPicture(request.getParameter("picture"));
        product.setPrice(new BigDecimal(request.getParameter("price")));
        product.setCount(Integer.parseInt(request.getParameter("count")));
        product.setNote(request.getParameter("note"));
        ProductDAO productDAO = new ProductDAO();
        String entity = (String) session.getAttribute("entity");
        if (entity != null) {
            switch (entity) {
                case "Выпечка":
                    Bakery bakery = new Bakery(product);
                    LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
                    bakery.setDate(Timestamp.valueOf(date));
                    bakery.setWeight(Integer.parseInt(request.getParameter("weight")));
                    productDAO.save(bakery);
                    break;
                case "Десерты":
                    Desert desert = new Desert(product);
                    desert.setWeight(Integer.parseInt(request.getParameter("weight")));
                    desert.setFirm(request.getParameter("firm"));
                    productDAO.save(desert);
                    break;
                case "Напитки":
                    Drink drink = new Drink(product);
                    drink.setHot(Boolean.parseBoolean(request.getParameter("hot")));
                    drink.setVolume(Float.parseFloat(request.getParameter("vol")));
                    productDAO.save(drink);
                    break;
            }
            session.removeAttribute("entity");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/products");
            dispatcher.forward(request, response);
        }
    }
%>
<form method="post">
    <table>
        <tr>
            <th>Название: </th>
            <td><input class="editor_input" name="name" type="text"></td>
        </tr>
        <tr>
            <th>Изображение: </th>
            <td><input class="editor_input" name="picture" type="text" placeholder="Введите локальный адрес изображения"></td>
        </tr>
        <tr>
            <th>Цена: </th>
            <td><input class="editor_input" name="price" step="any" type="number" min="1"></td>
        </tr>
        <tr>
            <th>Кол-во в наличии: </th>
            <td><input class="editor_input" name="count" type="number" min="-1" placeholder="Введите '-1' для продуктов, которые готовятся после заказа"></td>
        </tr>
        <tr>
            <th>Описание: </th>
            <td><textarea cols="80" rows="5" style="resize: none" name="note"></textarea></td>
        </tr>
        <%
            String entity = request.getParameter("product_add");
            session.setAttribute("entity", entity);
            switch (entity) {
                case "Выпечка":
        %>
        <tr>
            <th>Вес: </th>
            <td><input class="editor_input" name="weight" type="number" min="0"></td>
        </tr>
        <tr>
            <th>Дата изготовления: </th>
            <td><label><input class="editor_input" name="date" type="datetime-local"></label></td>
        </tr>
        <%
                    break;
                case "Десерты":
        %>
        <tr>
            <th>Вес: </th>
            <td><input class="editor_input" name="weight" type="number" min="0"></td>
        </tr>
        <tr>
            <th>Изготовитель: </th>
            <td><input class="editor_input" name="firm" type="text"></td>
        </tr>
        <%
                    break;
                case "Напитки":
        %>
        <tr>
            <th>Объём: </th>
            <td><input class="editor_input" name="vol" type="number" min="0" step="any"></td>
        </tr>
        <tr>
            <th>Горячий напиток: </th>
            <td><input name="hot" type="checkbox"></td>
        </tr>
        <%
                    break;
            }
        %>
    </table>

    <input type="submit" name="save" value="Сохранить">
</form>

<br><br>
<a href="/">Вернуться в меню</a>
</body>
</html>

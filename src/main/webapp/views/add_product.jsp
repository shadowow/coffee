<%@ page import="com.coffee.dao.ProductDAO" %>
<%@ page import="com.coffee.logic.Bakery" %>
<%@ page import="com.coffee.logic.Desert" %>
<%@ page import="com.coffee.logic.Drink" %>
<%@ page import="com.coffee.logic.Product" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.04.2016
  Time: 2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="/views/errorPage.jsp"%>
<html>
<head>
    <title>Добавление продукта</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>
<%!
    private static enum Entity {
        BAKERY, DRINK, DESERT;
    }
%>
<%
    request.setCharacterEncoding("UTF-8");
    // Общие поля
    String name = "";
    String picture = "";
    String price = "";
    String count = "";
    String note = "";
    //Другие поля
    String volume = "";
    LocalDateTime date = null;
    String firm = "";
    String weight = "";
    boolean hot = false;
    Entity entity = null;
    String entityString = request.getParameter("product_add");
    // Если мы перешли на страницу, чтобы добавить новый продукт:
    if (entityString != null) {
        switch (entityString) {
            case "Выпечка":
                entity = Entity.BAKERY;
                break;
            case "Десерты":
                entity = Entity.DESERT;
                break;
            case "Напитки":
                entity = Entity.DRINK;
                break;
            default:
                entity = Entity.BAKERY;
                break;
        }
        session.setAttribute("entity", entity);
    } else {
        // Если перешли, чтобы изменить продукт:
        entityString = request.getParameter("edit");
        if (entityString != null) {
            try {
                switch (entityString) {
                    case "Редактировать изделие":
                        entity = Entity.BAKERY;
                        int id = Integer.parseInt(request.getParameter("bakery_select"));
                        Optional<Product> result = new ProductDAO().findByID(id);
                        if (result.isPresent()) {
                            Bakery bakery = (Bakery) result.get();
                            name = bakery.getName();
                            picture = bakery.getPicture();
                            price = String.valueOf(bakery.getPrice());
                            count = String.valueOf(bakery.getCount());
                            note = bakery.getNote();
                            date = bakery.getDate().toLocalDateTime();
                            weight = String.valueOf(bakery.getWeight());
                            session.setAttribute("id", id);
                        } else {
%>
<script>alert('Не удалось найти продукт в базе данных!');</script>
<%
        }
        break;
    case "Редактировать десерт":
        entity = Entity.DESERT;
        id = Integer.parseInt(request.getParameter("desert_select"));
        Optional<Desert> resultDesert = new ProductDAO().findDesertByID(id);
        if (resultDesert.isPresent()) {
            Desert desert = resultDesert.get();
            name = desert.getName();
            picture = desert.getPicture();
            price = String.valueOf(desert.getPrice());
            count = String.valueOf(desert.getCount());
            note = desert.getNote();
            firm = desert.getFirm();
            weight = String.valueOf(desert.getWeight());
            session.setAttribute("id", id);
        } else {
%>
<script>alert('Не удалось найти продукт в базе данных!');</script>
<%
        }
        break;
    case "Редактировать напиток":
        entity = Entity.DRINK;
        id = Integer.parseInt(request.getParameter("drink_select"));
        Optional<Drink> resultDrink = new ProductDAO().findDrinkByID(id);
        if (resultDrink.isPresent()) {
            Drink drink = resultDrink.get();
            name = drink.getName();
            picture = drink.getPicture();
            price = String.valueOf(drink.getPrice());
            count = String.valueOf(drink.getCount());
            note = drink.getNote();
            volume = String.valueOf(drink.getVolume());
            hot = drink.isHot();
            session.setAttribute("id", id);
        } else {
%>
<script>alert('Не удалось найти продукт в базе данных!');</script>
<%
                        }
                        break;
                    default:
                        entity = Entity.BAKERY;
                        break;
                }
            } catch (NumberFormatException e) {
%>
<script>alert('Не удалось найти продукт в базе данных!');</script>
<%
            }
            session.setAttribute("entity", entity);
        }
    }
    if (request.getParameter("save") != null) {
        Product product = new Product();
        if (session.getAttribute("id") != null) {
            int id = (int) session.getAttribute("id");
            product.setID(id);
        }
        product.setName(request.getParameter("name"));
        product.setPicture(request.getParameter("picture"));
        product.setPrice(new BigDecimal(request.getParameter("price")));
        product.setCount(Integer.parseInt(request.getParameter("count")));
        product.setNote(request.getParameter("note"));

        entity = (Entity) session.getAttribute("entity");
        if (entity != null) {
            switch (entity) {
                case BAKERY:
                    Bakery bakery = new Bakery(product);
                    LocalDateTime ldc = LocalDateTime.parse(request.getParameter("date"));
                    bakery.setDate(Timestamp.valueOf(ldc));
                    bakery.setWeight(Integer.parseInt(request.getParameter("weight")));
                    new ProductDAO().save(bakery);
                    break;
                case DESERT:
                    Desert desert = new Desert(product);
                    desert.setWeight(Integer.parseInt(request.getParameter("weight")));
                    desert.setFirm(request.getParameter("firm"));
                    new ProductDAO().save(desert);
                    break;
                case DRINK:
                    Drink drink = new Drink(product);
                    drink.setHot(Boolean.parseBoolean(request.getParameter("hot")));
                    drink.setVolume(Float.parseFloat(request.getParameter("vol")));
                    new ProductDAO().save(drink);
                    break;
            }
            session.removeAttribute("entity");
            session.removeAttribute("id");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/products");
            dispatcher.forward(request, response);
        }
    }
%>
<form method="post">
    <table>
        <tr>
            <th>Название: </th>
            <td><input class="editor_input" name="name" type="text" value="<%=name%>"></td>
        </tr>
        <tr>
            <th>Изображение: </th>
            <td><input class="editor_input" name="picture" type="text" placeholder="Введите локальный адрес изображения" value="<%=picture%>"></td>
        </tr>
        <tr>
            <th>Цена: </th>
            <td><input class="editor_input" name="price" step="any" type="number" min="1" value="<%=price%>"></td>
        </tr>
        <tr>
            <th>Кол-во в наличии: </th>
            <td><input class="editor_input" name="count" type="number" min="-1" placeholder="Введите '-1' для продуктов, которые готовятся после заказа" value="<%=count%>"></td>
        </tr>
        <tr>
            <th>Описание: </th>
            <td><textarea cols="80" rows="5" style="resize: none" name="note" id="note"><%=note%></textarea></td>
        </tr>
        <%
            switch (entity) {
                case BAKERY:
        %>
        <tr>
            <th>Вес: </th>
            <td><input class="editor_input" name="weight" type="number" min="0" value="<%=weight%>"></td>
        </tr>
        <tr>
            <th>Дата изготовления: </th>
            <td><label><input class="editor_input" name="date" type="datetime-local" value="<%=date%>"></label></td>
        </tr>
        <%
                break;
            case DESERT:
        %>
        <tr>
            <th>Вес: </th>
            <td><input class="editor_input" name="weight" type="number" min="0" value="<%=weight%>"></td>
        </tr>
        <tr>
            <th>Изготовитель: </th>
            <td><input class="editor_input" name="firm" type="text" value="<%=firm%>"></td>
        </tr>
        <%
                break;
            case DRINK:
        %>
        <tr>
            <th>Объём: </th>
            <td><input class="editor_input" name="vol" type="number" min="0" step="any" value="<%=volume%>"></td>
        </tr>
        <tr>
            <th>Горячий напиток: </th>
            <td><input name="hot" type="checkbox" checked="<%=hot%>"></td>
        </tr>
        <%
                    break;
            }
        %>
    </table>

    <input type="submit" id="save" name="save" value="Сохранить">
</form>

<br><br>
<a href="/">Вернуться в меню</a>
<script src="/resources/js/products.js"></script>
</body>
</html>

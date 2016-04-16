<%@ page import="com.coffee.dao.OrderDAO" %>
<%@ page import="com.coffee.dao.ProductDAO" %>
<%@ page import="com.coffee.dao.StatusDAO" %>
<%@ page import="com.coffee.logic.BasketEntry" %>
<%@ page import="com.coffee.logic.Order" %>
<%@ page import="com.coffee.logic.Product" %>
<%@ page import="com.coffee.service.Basket" %>
<%@ page import="java.math.BigDecimal" %><%--
  Created by IntelliJ IDEA.
  User: Юленька
  Date: 15.04.2016
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Оформление заказа</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>
<div id = "order_div">
<%
    Basket basket = (Basket) session.getAttribute("basket");
    if (basket == null || basket.getPositions().isEmpty()) {
%>
<h2>Ваша корзина пуста.</h2>
<%  } else {
    BigDecimal total = BigDecimal.valueOf(0);
%>
<h3>Ваша корзина: </h3>
<%  for (BasketEntry pos : basket.getPositions()) {
    Product product = new ProductDAO().findByID(pos.getProductID()).get();
    total = total.add(product.getPrice().multiply(BigDecimal.valueOf(pos.getCount())));
%>
<li>
    <%=product.getName()%> <%=pos.getCount()%> <%=product.getPrice()%> <a href="/main?remove=<%=pos.getProductID()%>">Убрать</a>
</li>
<%  } %>
ИТОГО: <%=total%>
<form method="post">
    <table>
        <tr>
            <td>Телефон</td>
            <td><input name="phone" type="text" placeholder="Введите номер телефона"></td>
        </tr>
        <tr>
            <td>Улица</td>
            <td><input name="street" type="text" placeholder="Введите улицу"></td>
        </tr>
        <tr>
            <td>Дом</td>
            <td><input name="building" type="text" placeholder="Введите номер дома"></td>
        </tr>
        <tr>
            <td>Квартира</td>
            <td><input name="appartment" type="text" placeholder="Введите номер квартиры"></td>
        </tr>
    </table>
    <br>
    Примечание к заказу: <br>
    <textarea cols="80" rows="5" style="resize: none" name="note"></textarea> <br>
    <input type="submit" name="ok_btn" value="Подтвердить">
</form>
<%
        if (request.getParameter("ok_btn") != null) {
            Order order = new Order(basket);
            order.setStatus(new StatusDAO().findByName("Ожидание подтверждения").get());
            order.setPhone(request.getParameter("phone"));
            order.setStreet(request.getParameter("street"));
            order.setBuilding(request.getParameter("building"));
            String parameter = request.getParameter("appartment");
            if (!parameter.isEmpty()) {
                order.setAppartment(parameter);
            }
            parameter = request.getParameter("note");
            if (!parameter.isEmpty()) {
                order.setNote(parameter);
            }
            new OrderDAO().save(order);
            basket.clearBasket();
            request.setAttribute("phone", order.getPhone());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/final");
            dispatcher.forward(request, response);
        }
    }
%>
<br><br>
<a href="/">Вернуться в меню</a>
</div>
</body>
</html>

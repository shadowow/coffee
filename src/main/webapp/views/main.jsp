<%@ page import="com.coffee.dao.ProductDAO" %>
<%@ page import="com.coffee.logic.*" %>
<%@ page import="com.coffee.service.Basket" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" hot="text/css" href="/resources/css/main.css">
    <title>Coffee</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>
<form action="/main" method="post">
    <p>
        <table>
            <tr>
                <td><button type="submit" name="products_menu1">Выпечка</button></td>
                <td><button type="submit" name="products_menu2">Десерты</button></td>
                <td><button type="submit" name="products_menu3">Напитки</button></td>
            </tr>
        </table>
    </p>

    <%
        ProductDAO productDAO = new ProductDAO();

        // Берём из сессии корзину или создаём новую, если её ещё нет
        Basket basket = (Basket) session.getAttribute("basket");
        if (basket == null) {
            basket = new Basket();
            session.setAttribute("basket", basket);
        }
        // Удаление из корзины
        String productToRemove = request.getParameter("remove");
        if (productToRemove != null) {
            basket.removeFromBasket(Integer.parseInt(productToRemove));
        }

        // Добавляем элемент в корзину, если нажата кнопка "Добавить..."
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = (String) parameterNames.nextElement();
            if (parameterName.contains("add_product")) {
                int productID = Integer.parseInt(parameterName.replace("add_product", ""));
                int count = Integer.parseInt(request.getParameter("needed_count"+productID));
                Optional<Product> optional = productDAO.findByID(productID);
                if (optional.isPresent()) {
                    Product product = optional.get();
                    if (product.getCount() == -1 || count <= product.getCount()) {
                        BasketEntry basketEntry = new BasketEntry(count, product);
                        if (!basket.putInBasket(basketEntry)) {
    %><script>alert('Продукт, который вы пытаетесь добавить, уже в корзине!');</script><%
    }
} else {
%><script>alert('Вы указали большее кол-во, чем есть в наличии!');</script><%
    }
} else {
%><script>alert('Не удалось добавить выбранный продукт в корзину!');</script><%
            }
            break;
        }
    }
        // Отрисовка корзины
        List<BasketEntry> productsInBasket = basket.getPositions();
    if (!productsInBasket.isEmpty()) {
        BigDecimal total = BigDecimal.valueOf(0);
%>
    <h3>Ваша корзина: </h3>
    <%  for (BasketEntry pos : productsInBasket) {
            Product product = productDAO.findByID(pos.getProductID()).get();
        total = total.add(product.getPrice().multiply(BigDecimal.valueOf(pos.getCount())));
    %>
    <li>
        <%=product.getName()%> <%=pos.getCount()%> <%=product.getPrice()%> <a href="/main?remove=<%=pos.getProductID()%>">Убрать</a>
    </li>
    <%  } %>
    ИТОГО: <%=total%>
    <% }
        // Определяем, какой пункт меню выбран, отрисовываем таблицы
        parameterNames = request.getParameterNames();
        String paramName = "none";
        while (parameterNames.hasMoreElements()) {
            paramName = parameterNames.nextElement();
            if (paramName.contains("products_menu")) {
                break;
            } else {
                paramName = "none";
            }
        }
        if (paramName.equals("none")) {
            paramName = "products_menu1";
        }
        switch (paramName) {
            case "products_menu1":
                List<Bakery> bakeryList = productDAO.findAllBakery();
    %>
    <table>
        <tr>
            <th>Название</th><th>Изображение</th><th>Цена</th><th>В наличии</th><th>Описание</th>
            <th>Вес</th><th>Дата изготовления</th><th></th>
        </tr>
        <%
            for (Bakery bakery : bakeryList) { %>
        <tr>
            <td><%=bakery.getName()%></td>
            <td><img src="/resources/photos/<%=bakery.getPicture()%>"/></td>
            <td><%=bakery.getPrice()%></td>
            <td>
                <% if (bakery.getCount()!= -1) { %>
                <%=bakery.getCount()%>
                <% } %>
            </td>
            <td><%=bakery.getNote()%></td>
            <td><%=bakery.getWeight()%></td>
            <td><%=bakery.getDate()%></td>
            <td>
                <input type="number" class="count_input" name="needed_count<%=bakery.getID()%>" min=1 value="1"></input>
            </td>
            <td>
                <button type="submit" name="add_product<%=bakery.getID()%>">Добавить в корзину</button>
            </td>
        </tr>
        <% } %>
    </table>
    <%
            break;
        case "products_menu2":
            List<Desert> deserts = productDAO.findAllDeserts();
    %>
    <table>
        <tr>
            <th>Название</th><th>Изображение</th><th>Цена</th><th>В наличии</th><th>Описание</th>
            <th>Вес</th><th>Изготовитель</th><th></th>
        </tr>
        <%
            for (Desert desert : deserts) { %>
        <tr>
            <td><%=desert.getName()%></td>
            <td><img src="/resources/photos/<%=desert.getPicture()%>"/></td>
            <td><%=desert.getPrice()%></td>
            <td>
                <% if (desert.getCount()!= -1) { %>
                <%=desert.getCount()%>
                <% } %>
            </td>
            <td><%=desert.getNote()%></td>
            <td><%=desert.getWeight()%></td>
            <td><%=desert.getFirm()%></td>
            <td>
                <input type="number" class="count_input" name="needed_count<%=desert.getID()%>" min=1 value="1"></input>
            </td>
            <td>
                <button type="submit" name="add_product<%=desert.getID()%>">Добавить в корзину</button>
            </td>
        </tr>
        <% } %>
    </table>
    <%
            break;
        case "products_menu3":
            List<Drink> drinks = productDAO.findAllDrinks();
    %>
    <table>
        <tr>
            <th>Название</th><th>Изображение</th><th>Цена</th><th>В наличии</th><th>Описание</th>
            <th>Объём</th><th>Тип</th><th></th>
        </tr>
        <%
            for (Drink drink : drinks) { %>
        <tr>
            <td><%=drink.getName()%></td>
            <td><img src="/resources/photos/<%=drink.getPicture()%>"/></td>
            <td><%=drink.getPrice()%></td>
            <td>
                <% if (drink.getCount()!= -1) { %>
                <%=drink.getCount()%>
                <% } %>
            </td>
            <td><%=drink.getNote()%></td>
            <td><%=drink.getVolume()%></td>
            <td>
                <% if (drink.isHot()) { %>
                горячий
                <% } else { %>
                холодный
                <% } %>
            </td>
            <td>
                <input type="number" class="count_input" name="needed_count<%=drink.getID()%>" min=1 value="1"></input>
            </td>
            <td>
                <button type="submit" name="add_product<%=drink.getID()%>">Добавить в корзину</button>
            </td>
        </tr>
        <% } %>
    </table>
    <%
                break;
        }
    %>
    <br><br>
</form>
<form action="/make_order" method="post">
    <input type="submit" value="Оформить заказ"/>
</form>
<br><br>
<a href="/">Вернуться в меню</a>
</body>
</html>
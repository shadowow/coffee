<%@ page import="com.coffee.dao.OrderDAO" %>
<%@ page import="com.coffee.logic.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.coffee.dao.StatusDAO" %>
<%@ page import="com.coffee.logic.Status" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.coffee.dao.HibernateUtil" %>
<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15.04.2016
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заказы</title>
</head>
<body>
<h2>Заказы:</h2>
<form method="post">
    <table>
        <tr>
            <th>Номер</th><th>Адрес</th><th>Телефон</th><th>Статус</th>
        </tr>
        <%
            OrderDAO orderDAO = new OrderDAO();
            StatusDAO statusDAO = new StatusDAO();
            try (Session hibernateSession = HibernateUtil.getSessionFactory().openSession()) {
                // Обновление статуса заказа
                Enumeration<String> parameterNames = request.getParameterNames();
                while (parameterNames.hasMoreElements()) {
                    String name = parameterNames.nextElement();
                    if (name.contains("update")) {
                        int orderID = Integer.parseInt(name.replace("update", ""));
                        int statusID = Integer.parseInt(request.getParameter("status" + orderID));
                        Order order = orderDAO.findByID(orderID, hibernateSession).get();
                        order.setStatus(statusDAO.findByID(statusID, hibernateSession).get());
                        orderDAO.update(order, hibernateSession);
                    }
                }
                // Удаление заказа
                parameterNames = request.getParameterNames();
                while (parameterNames.hasMoreElements()) {
                    String name = parameterNames.nextElement();
                    if (name.contains("delete")) {
                        int orderID = Integer.parseInt(name.replace("delete", ""));
                        orderDAO.delete(orderID, hibernateSession);
                    }
                }

                List<Status> statuses = statusDAO.findAll(hibernateSession);
                List<Order> orderList = orderDAO.findAll(hibernateSession);
                for (Order order : orderList) {
        %>
        <tr>
            <td><%=order.getNumber()%></td>
            <td><%=order.getAddress()%></td>
            <td><%=order.getPhone()%></td>
            <td>
                <select name="status<%=order.getNumber()%>">
                    <%
                        for (Status status : statuses) {
                    %>
                    <option value="<%=status.getID()%>"
                <% if (status.getID() == order.getStatus().getID()) { %> selected <% } %>>
                        <%=status.getStatus()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
            <td>
                <input type="submit" name="update<%=order.getNumber()%>" value="Изменить статус">
            </td>
            <td>
                <input type="submit" name="delete<%=order.getNumber()%>" value="Удалить">
            </td>
        </tr>
        <%
            }
        %>
    </table>
</form>

<%  } %>

<br><br>
<a href="/">Вернуться в меню</a>
</body>
</html>

package com.coffee;

import com.coffee.dao.ProductDAO;
import com.coffee.logic.Product;

import java.sql.SQLException;

/**
 * Created by Юленька on 26.02.2016.
 */
public class Main {

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getByID(1);
    }
}

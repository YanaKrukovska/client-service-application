package ua.edu.ukma.ykrukovska.practice4;

import ua.edu.ukma.ykrukovska.practice2.storage.Product;

import java.sql.*;
import java.util.List;

public class StorageRepository {
    private static final String DB_URL = "jdbc:sqlite:C:/sqlite/storage.db";

    private Connection connection;

    public void connect() {
        connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Product newProduct) {
        String query = "INSERT INTO storage(product_name, group_name, amount, price) VALUES(?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newProduct.getName());
            statement.setString(2, newProduct.getGroups().get(0));
            statement.setInt(3, newProduct.getAmount());
            statement.setDouble(4, newProduct.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(List<Product> productList) {
        for (Product product : productList) {
            save(product);
        }
    }

    public void update(String productName, String newProductName, String groupName, int amount, double price) {
        try {
            String query = "UPDATE storage SET product_name=?, group_name=?, amount=?, price=? WHERE product_name=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newProductName);
            statement.setString(2, groupName);
            statement.setInt(3, amount);
            statement.setDouble(4, price);
            statement.setString(5, productName);
            statement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public ResultSet findByProductName(String productName) {
        PreparedStatement statement = null;
        try {
            String query = "SELECT * FROM storage WHERE product_name=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, productName);
            return statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public ResultSet findByGroup(String groupName) {
        PreparedStatement statement = null;
        try {
            String query = "SELECT * FROM storage WHERE group_name=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, groupName);
            return statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void delete(String productName) {
        try {
            String query = "DELETE from storage WHERE product_name=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productName);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

}




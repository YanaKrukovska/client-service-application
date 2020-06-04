package ua.edu.ukma.ykrukovska.practice4;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.edu.ukma.ykrukovska.practice2.storage.Product;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class StorageRepositoryTest {
    static StorageRepository databaseService;
    static Connection dbConnection;

    @BeforeAll
    static void setConnection() {
        databaseService = new StorageRepository();
        databaseService.connect();
        dbConnection = databaseService.getConnection();
    }



    public int calculateTableSize() throws SQLException {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM storage");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet.getInt(1);
    }

    @Test
    public void addOneProduct() throws SQLException {
        databaseService.save(new Product("whiskas", new LinkedList<>(Collections.singletonList("cat food")), 340, 12.70));

        String query = "SELECT * FROM storage WHERE product_name=?";
        PreparedStatement statement;
        ResultSet resultSet = null;
        try {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, "whiskas");
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals("whiskas", resultSet.getString("product_name"));
    }

    @Test
    public void addListOfProducts() throws SQLException {

        int initialDBSize = calculateTableSize();

        List<Product> productList = new LinkedList<>(Arrays.asList(
                new Product("kitiket salmon", new LinkedList<>(Collections.singletonList("cat food")), 340, 12.70),
                new Product("kitiket chicken", new LinkedList<>(Collections.singletonList("cat food")), 220, 12.70),
                new Product("kitiket beef", new LinkedList<>(Collections.singletonList("cat food")), 10, 17.00)));

        databaseService.save(productList);
        int newDBSize = calculateTableSize();

        Assertions.assertEquals(initialDBSize + productList.size(), newDBSize);
    }

    @Test
    public void updateProduct() throws SQLException {

        String initialGroup = "food";
        int initialAmount = 200;
        double initialPrice = 10;
        databaseService.save(new Product("butter", new LinkedList<>(Collections.singletonList(initialGroup)), initialAmount, initialPrice));
        String newGroup = "god food";
        int newAmount = 1000;
        double newPrice = 20;

        databaseService.update("butter", "butter", newGroup, newAmount, newPrice);

        String query = "SELECT * FROM storage WHERE product_name=?";
        PreparedStatement statement;
        ResultSet resultSet = null;
        try {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, "butter");
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals("butter", resultSet.getString("product_name"));
        Assertions.assertEquals(newGroup, resultSet.getString("group_name"));
        Assertions.assertEquals(newAmount, resultSet.getInt("amount"));
        Assertions.assertEquals(newPrice, resultSet.getDouble("price"));
    }

    //TODO: fix exception ResultSet closed
    @Test
    public void findProductByName() {
        ResultSet resultSet = databaseService.findByProductName("whiskas");
        try {
            Assertions.assertEquals("whiskas", resultSet.getString("product_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteProduct() {
        databaseService.save(new Product("bat", new LinkedList<>(Collections.singletonList("chinese")), 1, 0.0));
        databaseService.delete("bat");

        String query = "SELECT * FROM storage WHERE product_name=?";
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, "bat");
            resultSet = statement.executeQuery();
            Assertions.assertNull(resultSet.getString("product_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @AfterAll
    public static void tearDown() throws Exception {
        dbConnection.close();
        databaseService.getConnection().close();
    }

}
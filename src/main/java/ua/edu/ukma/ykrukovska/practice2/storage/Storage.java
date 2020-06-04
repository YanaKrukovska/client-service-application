package ua.edu.ukma.ykrukovska.practice2.storage;

public interface Storage {

    double getProductAmount(String productName);
    void addProduct(String productName, int amount);
    void withdrawProduct(String productName, int amount);
    void addProductGroup(String productGroupName);
    void addProductToGroup(String productGroupName, String productName);
    void setProductPrice(String productName, double price);

}

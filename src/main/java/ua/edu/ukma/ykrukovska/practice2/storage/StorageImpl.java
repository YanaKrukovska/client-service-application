package ua.edu.ukma.ykrukovska.practice2.storage;

import java.util.*;

public class StorageImpl implements Storage {

    private List<String> groups = new LinkedList<>();
    private Map<String, Product> products = new HashMap<>();


    @Override
    public double getProductAmount(String productName) {
        return products.get(productName).getAmount();
    }

    @Override
    public void addProduct(String productName, int amount) {
        products.get(productName).setAmount(products.get(productName).getAmount() + amount);

    }

    @Override
    public void withdrawProduct(String productName, int amount) {
        products.get(productName).setAmount(products.get(productName).getAmount() - amount);
    }

    @Override
    public void addProductGroup(String productGroupName) {
        if (!groups.contains(productGroupName)) {
            groups.add(productGroupName);
        }
    }

    @Override
    public void addProductToGroup(String productGroupName, String productName) {

        Product product = products.get(productName);
        if (product == null) {
            products.put(productName, new Product(productName, new LinkedList<String>(Collections.singleton(productGroupName)), 0, 0));
        }
        if (product != null && !product.getGroups().contains(productGroupName)) {
            product.getGroups().add(productGroupName);
        }

    }

    @Override
    public void setProductPrice(String productName, double price) {
        products.get(productName).setPrice(price);
    }

    public List<String> getGroups() {
        return groups;
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }
}

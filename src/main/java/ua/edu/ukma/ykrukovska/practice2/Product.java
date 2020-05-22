package ua.edu.ukma.ykrukovska.practice2;

import java.util.LinkedList;
import java.util.List;

public class Product {

    private List<String> groups;
    private String name;
    private double amount;
    private double price;

    public Product(String name, LinkedList<String> group,  double amount, double price) {
        this.groups = group;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

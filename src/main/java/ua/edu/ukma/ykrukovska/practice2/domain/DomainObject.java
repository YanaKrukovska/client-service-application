package ua.edu.ukma.ykrukovska.practice2.domain;

import java.math.BigDecimal;

public class DomainObject {

    private Operations operation;
    private String product;
    private BigDecimal amount;

    public DomainObject(Operations operation, String product, BigDecimal amount) {
        this.operation = operation;
        this.product = product;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "DomainObject: " + "operation=" + operation +
                ", product='" + product + '\'' +
                ", amount=" + amount +
                '}';
    }
}

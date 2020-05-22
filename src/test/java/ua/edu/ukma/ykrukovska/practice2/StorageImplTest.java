package ua.edu.ukma.ykrukovska.practice2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class StorageImplTest {

    private static StorageImpl storage;

    @BeforeAll
    static void setStorage() {
        storage = new StorageImpl();
        storage.setGroups(new LinkedList<>(Arrays.asList("cat food", "human food", "cars")));
        storage.getProducts().put("whiskas", new Product("whiskas", new LinkedList<>(Collections.singletonList("cat food")), 50, 12));
        storage.getProducts().put("kitikat", new Product("kitikat", new LinkedList<>(Collections.singletonList("cat food")), 40, 13));
        storage.getProducts().put("ford focus", new Product("ford focus", new LinkedList<>(Collections.singletonList("cars")), 1, 100));
        storage.getProducts().put("carrot", new Product("carrot", new LinkedList<>(Collections.singletonList("human food")), 100, 8));
    }

    @Test
    void getAmount() {
        Assertions.assertEquals(50, storage.getProductAmount("whiskas"));
    }

    @Test
    void addProduct() {
        storage.addProduct("ford focus", 1);
        Assertions.assertEquals(2, storage.getProductAmount("ford focus"));

    }

    @Test
    void withdrawProduct() {
        storage.withdrawProduct("carrot", 10);
        Assertions.assertEquals(90, storage.getProductAmount("carrot"));

    }

    @Test
    void addProductGroupNew() {
        storage.addProductGroup("house");
        Assertions.assertTrue(storage.getGroups().contains("house"));
    }

    @Test
    void addExistingProductToGroup() {
        storage.addProductToGroup("cat food", "carrot");
        assertThat(storage.getProducts().get("carrot").getGroups().contains("human food"));
        assertThat(storage.getProducts().get("carrot").getGroups().contains("cat food"));
    }

    @Test
    void addNewProductToGroup() {
        storage.addProductToGroup("cat food", "celery");
        assertThat(storage.getProducts().get("celery").getGroups().contains("cat food"));
    }

    @Test
    void setProductPrice() {
        storage.addProductToGroup("cat food", "celery");
        double initialPrice = storage.getProducts().get("celery").getPrice();
        storage.getProducts().get("celery").setPrice(10);
        Assertions.assertNotEquals(initialPrice, storage.getProducts().get("celery").getPrice());
    }
}
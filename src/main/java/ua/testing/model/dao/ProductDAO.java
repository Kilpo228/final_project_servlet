package ua.testing.model.dao;

import ua.testing.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO extends GenericDAO<Product> {
    void save(Product product);
    void decreaseAmount(String enProductName, int amount);
    void updateAmountAndPrice(long cents, int amount, String name);
    void deleteByName(String name);
    Optional<Product> findProductByEnName(String name);
    Optional<Product> findProductByRuName(String name);
    Optional<Product> findProductByName(String name);
    List<Product> findAllAvailableProducts();
    List<Product> findAllOrderByEnName();
    List<Product> findAllOrderByRuName();
    List<Product> findAllOrderByAmount();
    List<Product> findAllOrderByCategory();
    List<Product> findAllOrderByPrice();
}

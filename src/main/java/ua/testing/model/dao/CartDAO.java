package ua.testing.model.dao;

import ua.testing.model.entity.Cart;

import java.util.List;

public interface CartDAO extends GenericDAO<Cart> {
    void save(Cart entity);
    List<Cart> findAllItemsByUsername(String username);
    void increaseItemAmount(String productName, String username);
    void decreaseItemAmount(String productName, String username);
    void deleteByUsername(String username);
    void deleteItem(Cart cart);
}

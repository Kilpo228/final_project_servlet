package ua.testing.model.dao;

import ua.testing.model.entity.Receipt;

import java.util.List;

public interface ReceiptDAO extends GenericDAO<Receipt> {
    void save(Receipt receipt);
    List<Receipt> findAllReceiptByUsername(String username);
}

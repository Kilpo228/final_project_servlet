package ua.testing.model.dao;

import ua.testing.model.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientDAO extends GenericDAO<Ingredient> {
    void save(Ingredient ingredient);
    void increaseAmountByName(String name);
    void decreaseAmountByName(String name);
    void deleteByName(String name);
    int findAmountByName(String name);
    Optional<Ingredient> findByName(String name);
    List<Ingredient> findAllOrderByEnName();
    List<Ingredient> findAllOrderByRuName();
    List<Ingredient> findAllOrderByAmount();
    List<Ingredient> findAllWithAmountGreaterThanZero();
}

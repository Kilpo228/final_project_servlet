package ua.testing.model.service;

import ua.testing.model.annotation.InjectByType;
import ua.testing.model.annotation.Service;
import ua.testing.model.dao.IngredientDAO;
import ua.testing.model.entity.Ingredient;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @InjectByType
    private IngredientDAO ingredientDAO;

    public List<Ingredient> parseQuery(HttpServletRequest request) {
        List<Ingredient> ingredients = ingredientDAO.findAll();

        Optional.ofNullable(request.getParameter("sort")).ifPresent(sort -> {
            switch (sort) {
                case "en":
                    ingredients.clear();
                    ingredients.addAll(ingredientDAO.findAllOrderByEnName());
                    break;
                case "ru":
                    ingredients.clear();
                    ingredients.addAll(ingredientDAO.findAllOrderByRuName());
                    break;
                case "amount":
                    ingredients.clear();
                    ingredients.addAll(ingredientDAO.findAllOrderByAmount());
                    break;
            }
        });

        return ingredients;
    }

    public void increaseIngredientAmount(String name) {
        ingredientDAO.increaseAmountByName(name);
    }

    public void decreaseIngredientAmount(String name) {
        ingredientDAO.decreaseAmountByName(name);
    }

    public String getAmountOfIngredient(String name) {
        return String.valueOf(ingredientDAO.findAmountByName(name));
    }

    public String saveNewIngredient(String enName, String ruName) {
        if (enName.matches("^[a-zA-Z]+$") && ruName.matches("^[а-яА-Я]+$")) {
            ingredientDAO.save(new Ingredient(enName, ruName, 100));
            return "ok";
        } else {
            return "name";
        }
    }

    public void deleteIngredient(String name) {
        ingredientDAO.deleteByName(name);
    }
}

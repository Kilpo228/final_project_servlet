package ua.testing.controller.command.imp.util;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.dao.IngredientDAO;
import ua.testing.model.dao.ProductDAO;
import ua.testing.model.entity.Ingredient;
import ua.testing.model.entity.Product;
import ua.testing.model.entity.ProductCategory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Controller
public class FillTables implements Command {
    private static boolean IS_FILLED = true;

    @InjectByType
    private IngredientDAO ingredientDAO;

    @InjectByType
    private ProductDAO productDAO;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (IS_FILLED) {
            IS_FILLED = false;
            ingredientDAO.save(new Ingredient("dough", "тесто", 100));
            ingredientDAO.save(new Ingredient("champignon", "шампиньоны", 100));
            ingredientDAO.save(new Ingredient("sausages", "колбаски", 100));
            ingredientDAO.save(new Ingredient("olives", "маслины", 100));
            ingredientDAO.save(new Ingredient("pineapples", "ананасы", 100));
            ingredientDAO.save(new Ingredient("cucumbers", "огурцы", 100));
            ingredientDAO.save(new Ingredient("mayonnaise", "майонез", 100));
            ingredientDAO.save(new Ingredient("ketchup", "кетчуп", 100));
            ingredientDAO.save(new Ingredient("tomatoes", "томаты", 100));
            ingredientDAO.save(new Ingredient("onion", "лук", 100));
            ingredientDAO.save(new Ingredient("salmon", "лосось", 100));
            ingredientDAO.save(new Ingredient("rice", "рис", 100));
            ingredientDAO.save(new Ingredient("chicken", "курица", 100));
            ingredientDAO.save(new Ingredient("beef", "говядина", 100));
            ingredientDAO.save(new Ingredient("cheese", "сыр", 100));
            ingredientDAO.save(new Ingredient("acne", "угорь", 100));
            ingredientDAO.save(new Ingredient("tofy", "тофу", 100));
            ingredientDAO.save(new Ingredient("caviar", "икра", 100));

            productDAO.save(new Product(ProductCategory.PIZZA, "Pizza BBQ", "Пицца Барбекю", 100, 1000L, Arrays.asList(
                    new Ingredient("dough"),
                    new Ingredient("sausages"),
                    new Ingredient("tomatoes"),
                    new Ingredient("cheese"),
                    new Ingredient("champignon")
            )));
            productDAO.save(new Product(ProductCategory.PIZZA, "Hawaiian pizza", "Гавайская пицца", 100, 900L, Arrays.asList(
                    new Ingredient("dough"),
                    new Ingredient("pineapples"),
                    new Ingredient("onion")
            )));
            productDAO.save(new Product(ProductCategory.PIZZA, "Meat pizza", "Мясная пицца", 100, 1300L, Arrays.asList(
                    new Ingredient("dough"),
                    new Ingredient("sausages"),
                    new Ingredient("onion"),
                    new Ingredient("beef"),
                    new Ingredient("tomatoes"),
                    new Ingredient("olives"),
                    new Ingredient("champignon")
            )));

            productDAO.save(new Product(ProductCategory.TEA, "Black tea", "Чёрный чай", 100, 100L, Collections.emptyList()));
            productDAO.save(new Product(ProductCategory.TEA, "Green tea", "Залёный чай", 100, 100L, Collections.emptyList()));

            productDAO.save(new Product(ProductCategory.JUICE, "Apple juice", "Яблочный сок", 100, 50L, Collections.emptyList()));
            productDAO.save(new Product(ProductCategory.JUICE, "Cherry juice", "Вишнёвый сок", 100, 50L, Collections.emptyList()));
            productDAO.save(new Product(ProductCategory.JUICE, "Peach juice", "Персиковый сок", 100, 50L, Collections.emptyList()));

            productDAO.save(new Product(ProductCategory.COLD_DRINKS, "Fanta", "Фанта", 100, 50L, Collections.emptyList()));
            productDAO.save(new Product(ProductCategory.COLD_DRINKS, "Pepsi", "Пепси", 100, 50L, Collections.emptyList()));
            productDAO.save(new Product(ProductCategory.COLD_DRINKS, "Coca-Cola", "Кока-Кола", 100, 50L, Collections.emptyList()));

            productDAO.save(new Product(ProductCategory.BURGERS, "BigMac", "БигМак", 100, 700L, Arrays.asList(
                    new Ingredient("ketchup"),
                    new Ingredient("cheese"),
                    new Ingredient("onion"),
                    new Ingredient("beef"),
                    new Ingredient("mayonnaise"),
                    new Ingredient("cucumbers")
            )));
            productDAO.save(new Product(ProductCategory.BURGERS, "DoubleCheeseBurger", "ДаблЧизБургер", 100, 600L, Arrays.asList(
                    new Ingredient("ketchup"),
                    new Ingredient("cheese"),
                    new Ingredient("cheese"),
                    new Ingredient("onion"),
                    new Ingredient("beef"),
                    new Ingredient("beef")
            )));
            productDAO.save(new Product(ProductCategory.BURGERS, "CheeseBurger", "ЧизБургер", 100, 500L, Arrays.asList(
                    new Ingredient("ketchup"),
                    new Ingredient("cheese"),
                    new Ingredient("onion"),
                    new Ingredient("beef")
            )));

            productDAO.save(new Product(ProductCategory.SUSHI, "Red Dragon", "Красный Дракон", 100, 900L, Arrays.asList(
                    new Ingredient("rice"),
                    new Ingredient("salmon"),
                    new Ingredient("tofy")
            )));

            productDAO.save(new Product(ProductCategory.SUSHI, "Unagi Maki", "Унаги Маки", 100, 850L, Arrays.asList(
                    new Ingredient("rice"),
                    new Ingredient("acne"),
                    new Ingredient("tofy")
            )));

            productDAO.save(new Product(ProductCategory.SUSHI, "Sakana Maki", "Сакана Маки", 100, 850L, Arrays.asList(
                    new Ingredient("rice"),
                    new Ingredient("salmon"),
                    new Ingredient("tofy"),
                    new Ingredient("caviar")
            )));
        }

        response.sendRedirect("/app");
    }
}

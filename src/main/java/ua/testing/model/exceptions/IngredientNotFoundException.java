package ua.testing.model.exceptions;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(String name) {
        super("Ingredient wasn't found with such name: " + name);
    }
}

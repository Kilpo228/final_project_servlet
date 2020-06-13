package ua.testing.model.exceptions;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String name) {
        super("Cart wasn't found with such name: " + name);
    }
}

package ua.testing.model.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String name) {
        super("Product wasn't found with such name: " + name);
    }
}

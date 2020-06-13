package ua.testing.model.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String name) {
        super("User wasn't found with such USERNAME: " + name);
    }
}

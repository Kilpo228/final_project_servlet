package ua.testing.model.exceptions;

public class MultipleImplementationException extends RuntimeException {
    public MultipleImplementationException(Class<?> clazz) {
        super(clazz + " has 0 or more than 1 implementation");
    }
}

package nl.joelchrist.spotitube.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(Class clazz) {
        super(String.format("Couldn't find instance of class: %s", clazz.getName()));
    }
}

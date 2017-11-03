package nl.joelchrist.spotitube.exceptions;

public class EntityNotFoundException extends Exception {
    private Class clazz;

    public EntityNotFoundException(Class clazz) {
        super(String.format("Couldn't find instance of class: %s", clazz.getName()));
        this.clazz = clazz;
    }
}

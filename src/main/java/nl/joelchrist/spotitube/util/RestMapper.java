package nl.joelchrist.spotitube.util;

public interface RestMapper<T, K> {
    T toRest(K nonRest);
    K fromRest(T rest);
}
